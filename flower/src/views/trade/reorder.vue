<script setup>
import { ref } from 'vue';
const total = ref(20)
const pageNum = ref(1)
const pageSize = ref(4)
const reorderList = ref([
 {
    rid:1,
    ordid:2,
    rprice:123,
    rstate:'未通过',
    reason:'afsa',
    rtime:'2321-123'  
 },
 {
    rid:1,
    ordid:2,
    rprice:123,
    rstate:'已通过',
    reason:'afsa',
    rtime:'2321-123'  
 },{
    rid:1,
    ordid:2,
    rprice:123,
    rstate:'待处理',
    reason:'afsa',
    rtime:'2321-123'  
 },

])

const selectedState = ref('')
import { buyerListService } from "@/api/buyer.js"
import { flowerListService } from "@/api/flower.js"
import { reorderListService, changeReorderStateService } from "@/api/trade.js"
import { useBuyerStore } from '@/stores/index.js'
import { ElMessage } from 'element-plus';

const buyerStore = useBuyerStore()
const getReorderList = async () => {
  const bid = ref('')
  if (!buyerStore.buyerInfo.isSeller) {
    bid.value = buyerStore.buyerInfo.bid
  }
  const result = await reorderListService(pageNum.value, bid.value, selectedState.value)

  reorderList.value = result.data.items
  total.value = result.data.total

  for (let i = 0; i < reorderList.value.length; i++) {
    let noword = reorderList.value[i]
    noword.rtime = noword.rtime.substring(0, 10)
    noword.rprice = noword.rprice.toFixed(1)
    if (noword.rstate == '0') {
      noword.rstate = '未通过'
    } else if (noword.rstate == '1') {
      noword.rstate = '已通过'
    } else if (noword.rstate == '2') {
      noword.rstate = '待处理'
    } 

  }
}

getReorderList()

const changeReorderState = async (rid, rstate) => {
  const result = await changeReorderStateService(rid, rstate)
  getReorderList()
  ElMessage.success("操作成功")

}


</script>

<template>
  <el-form inline>
    <el-form-item label="售后状态：">
      <el-select placeholder="请选择" v-model="selectedState" style="width: 100px;">
        <el-option label="未通过" value="0"></el-option>
        <el-option label="已通过" value="1"></el-option>
        <el-option label="待处理" value="2"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="getReorderList()">搜索</el-button>
      <el-button @click="selectedState = ''; getReorderList()">重置</el-button>
    </el-form-item>
  </el-form>
  <el-card class="page-container ">
    <el-table class="dataTable" :data="reorderList" style="width: 100% ">
        <el-table-column  label="售后编号" width="100" prop="rid"></el-table-column>
      <el-table-column  label="订单编号" width="100" prop="ordid"></el-table-column>
    <el-table-column label="金额" prop="rprice"  width="100"></el-table-column>
      <el-table-column label="状态" prop="rstate"  width="100"></el-table-column>
      <el-table-column label="原因" prop="reason" ></el-table-column>
      <el-table-column label="创建时间" width="150" prop="rtime"></el-table-column>
      <el-table-column label="操作" width="150" style="align-items: center;">
        <template #default="{ row }">
            <el-button v-if="row.rstate == '未通过'" disabled plain type="danger">未通过</el-button>
            <el-button v-if="row.rstate == '已通过'" disabled plain type="success">已通过</el-button>
            
          <div v-if="!buyerStore.buyerInfo.isSeller">
          <el-button v-if="row.rstate == '待处理'" disabled plain type="primary">待处理</el-button>
          </div>
          <div v-else>
            <el-button @click="changeReorderState(row.rid,1)" v-if="row.rstate == '待处理'"  circle plain type="success">同意</el-button>
            <el-button @click="changeReorderState(row.rid,0)"  v-if="row.rstate == '待处理'"  circle plain type="danger">拒接</el-button>
          </div>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
    <!-- 分页条 -->

    <el-pagination small background layout="jumper, total,  prev, pager, next" :total="total"
      @current-change="getReorderList()" :page-size=pageSize v-model:current-page="pageNum"
      style="margin-top: 20px; justify-content: center" />

  </el-card>
  

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
.el-row {
  margin-bottom: 20px;
}
.dialog {
  display: flex;
  
  justify-content: center;
}
</style>