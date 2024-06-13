<script setup>
import { ref } from 'vue';
const total = ref(20)
const pageNum = ref(1)
const pageSize = ref(4)
const orderList = ref([
  {
  ordid:'1',
    bname: 's',
    fname: 'soyo',
    ordnum: 2,
    ordprice: 2,
    ordstate: '未发货',
    ctime: "2342"
  },
  {
    ordid:'1',
    bname: 's',
    fname: 'soyo',
    ordnum: 2,
    ordprice: 2,
    ordstate: '已发货',
    ctime: "2342"
  },
  {
    ordid:'1',
    bname: 's',
    fname: 'soyo',
    ordnum: 2,
    ordprice: 2,
    ordstate: '已签收',
    ctime: "2342"
  },
  {
    ordid:'1',
    bname: 's',
    fname: 'soyo',
    ordnum: 2,
    ordprice: 2,
    ordstate: '已售后',
    ctime: "2342"
  },
])
const buyerList = ref('')
const flowerList = ref('')
const selectedState = ref('')
import { buyerListService } from "@/api/buyer.js"
import { flowerListService } from "@/api/flower.js"
import { orderListService, changeOrdstateService } from "@/api/trade.js"
import { useBuyerStore } from '@/stores/index.js'
import { ElMessage } from 'element-plus';

const buyerStore = useBuyerStore()
const getOrderList = async () => {
  const bid = ref('')
  if (!buyerStore.buyerInfo.isSeller) {
    bid.value = buyerStore.buyerInfo.bid
  }
  const result = await orderListService(pageNum.value, bid.value, selectedState.value)
  // console.log(result.data)
  orderList.value = result.data.items
  total.value = result.data.total

  const result1 = await buyerListService()
  buyerList.value = result1.data
  const result2 = await flowerListService()
  flowerList.value = result2.data.items

  // console.log("bl",buyerList.value)
  // console.log(flowerList.value)
  for (let i = 0; i < orderList.value.length; i++) {
    let noword = orderList.value[i]
    for (let j = 0; j < flowerList.value.length; j++) {
      if (noword.fid == flowerList.value[j].fid) {
        noword.fname = flowerList.value[j].fname
      }
    }
    for (let k = 0; k < buyerList.value.length; k++) {
      if (noword.bid == buyerList.value[k].bid) {
        noword.bname = buyerList.value[k].bname
      }
    }
  }
  for (let i = 0; i < orderList.value.length; i++) {
    let noword = orderList.value[i]
    noword.ctime = noword.ctime.substring(0, 10)
    noword.ordprice = noword.ordprice.toFixed(1)
    if (noword.ordstate == '0') {
      noword.ordstate = '未发货'
    } else if (noword.ordstate == '1') {
      noword.ordstate = '已发货'
    } else if (noword.ordstate == '2') {
      noword.ordstate = '已签收'
    } else {
      noword.ordstate = '已售后'
    }

  }
}

getOrderList()

const changeOrdstate = async (ordid, ordstate) => {
  const result = await changeOrdstateService(ordid, ordstate)
  getOrderList()
  ElMessage.success("操作成功")

}

import { commentAddService } from "@/api/comment.js"
const dialogVisible = ref(false)
const commentstable = ref({
  bid: '',
  fid: '',
  fname: '',
  score: '',
  comments: ''
})
const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900'])
const commentAdd = async () => {
  const isNull = ref(false)
  if (commentstable.value.score == '' || commentstable.value.comments == '') {

    isNull.value = true
  }
  if (isNull.value) {
    ElMessage.error("不能为空哦")
  } else {
    commentstable.value.bid = buyerStore.buyerInfo.bid
    for (let i = 0; i < flowerList.value.length; i++) {
      if (flowerList.value[i].fname == commentstable.value.fname) {
        commentstable.value.fid = flowerList.value[i].fid;
        break
      }
    }

    const result = await commentAddService(commentstable.value)
    getOrderList()
    ElMessage.success("操作成功")

  }
}
</script>

<template>
  <el-form inline>
    <el-form-item label="订单状态：">
      <el-select placeholder="请选择" v-model="selectedState" style="width: 100px;">
        <el-option label="未发货" value="0"></el-option>
        <el-option label="已发货" value="1"></el-option>
        <el-option label="已签收" value="2"></el-option>
        <el-option label="已售后" value="3"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="getOrderList()">搜索</el-button>
      <el-button @click="selectedState = ''; getOrderList()">重置</el-button>
    </el-form-item>
  </el-form>
  <el-card class="page-container ">
    <el-table class="dataTable" :data="orderList" style="width: 100% ">
      <el-table-column  label="订单编号" width="100" prop="ordid"></el-table-column>
      <el-table-column label="用户名" width="150" prop="bname"></el-table-column>
      <el-table-column label="花卉名" prop="fname"> </el-table-column>

      <el-table-column label="数量"  prop="ordnum"></el-table-column>
      <el-table-column label="总价" prop="ordprice"></el-table-column>
      <el-table-column label="状态" prop="ordstate"></el-table-column>
      <el-table-column label="创建时间" width="200" prop="ctime"></el-table-column>
      <el-table-column label="操作" width="150" style="align-items: center;">
        <template #default="{ row }">
          <div v-if="!buyerStore.buyerInfo.isSeller">
            <el-button v-if="row.ordstate == '未发货'" circle plain type="primary"
              @click="ElMessage.success('催发货');">催</el-button>
            <el-button v-if="row.ordstate == '已发货'" circle plain type="success"
              @click="changeOrdstate(row.ordid, 2);">签收</el-button>
            <el-button v-if="row.ordstate == '已签收'" circle plain type="warning"
              @click="dialogVisible = true; commentstable.fname = row.fname">评价</el-button>
            <el-button v-if="row.ordstate !== '已售后'" circle plain type="danger">售后</el-button>
            <el-button v-else style="background-color: darkgrey"  plain>已售后</el-button>

          </div>
          <div v-else>
            <el-button v-if="row.ordstate == '未发货'" circle plain type="success"
              @click="changeOrdstate(row.ordid, 1)">发货</el-button>
            <el-button v-else-if="row.ordstate == '已售后'"  plain style="background-color: darkgrey" >已售后</el-button>
            <el-button v-else-if="row.ordstate == '已签收'" disabled circle plain type="primary">已签收</el-button>
            <el-button v-else disabled circle plain type="primary">已发货</el-button>
          </div>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
    <!-- 分页条 -->

    <el-pagination small background layout="jumper, total,  prev, pager, next" :total="total"
      @current-change="getOrderList()" :page-size=pageSize v-model:current-page="pageNum"
      style="margin-top: 20px; justify-content: center" />

  </el-card>

  <el-dialog title="评价" v-model="dialogVisible" width="30%">
    <div class="dialog">

      <div class="demo-rate-block">
        <span class="demonstration"></span>
        <label>评分:</label>
        <el-rate v-model="commentstable.score" :colors="colors" />
      </div>

    </div>
    <div class="dialog">
      <span>
        <label>评价:</label><input type="text" v-model="commentstable.comments">
      </span>
    </div>

    <template #footer>
      <span class="dialog">
        <el-button type="primary" @click="commentAdd(); dialogVisible = false; console.log(commentstable)">确定</el-button>
        <el-button type="warning"
          @click="dialogVisible = false; commentstable.score = ''; commentstable.comments = ''">取消</el-button>

      </span>
    </template>
  </el-dialog>

</template>





<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;
  justify-content: space-between;


  .image {
    width: 70px;
    display: block;
  }

}

.dialog {
  display: flex;
  margin: 15px;
  justify-content: center;
}
</style>