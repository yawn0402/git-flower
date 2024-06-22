<script setup>
import{ArrowLeftBold
  
}from  '@element-plus/icons-vue';

  import{useFlowerStore,useBuyerStore} from'@/stores/index.js'
  const flowerStore=useFlowerStore()
  // const fid=FlowerStore.selectedFlowerId
  const buyerStore=useBuyerStore()
 

  import { useRouter } from 'vue-router';
  import { ref } from 'vue';
  const router=useRouter()
  const drawerVisiable=ref(false)

  const pics=ref([
    {
      pid:1,
      paddress:''
    },
    {
      pid:2,
      paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/6426fd94-8ad2-4427-91be-eaa17a688cae.png'
      // https://big-event-ct.oss-cn-beijing.aliyuncs.com/6426fd94-8ad2-4427-91be-eaa17a688cae.png
    }
  ])
  
  const flowerDetail=ref({
    fid:1,
    fname:'rose',
    fprice:34.3,
    fintroduction:'很长很长很长哼唱很长很长很长哼唱很长27字',
    fnum:24
  })

  const flowerComments=ref([
    {
      comid:1,
      bname:'yawn',
      score:3,
      comments:'hoahoa',
     comtime:'2023-2-2'
    },
    {
      comid:2,
      name:'wenxin',
      score:4,
      comments:'下次还来',
      comtime:'2024-5-2'
    },
  ])
  const buyerList=ref([{
            "bid": 1,
            "bname": "yawn",
            "bpwd": "123456",
            "bsex": "男",
            "bavatar": "https://big-event-ct.oss-cn-beijing.aliyuncs.com/8a1664de-b5b5-4fee-8ea6-529f3294423f.png",
            "btele": "18390202",
            "baddress": "长沙"
        },
        {
            "bid": 2,
            "bname": "btest",
            "bpwd": "123456",
            "bsex": null,
            "bavatar": null,
            "btele": null,
            "baddress": "光之国"
        },])
  const selectNum = ref(1)
  const dialogVisible = ref(false)
  
  import{
    flowerDetailService,
    flowerPicsService,flowerCommentService
  }from "@/api/flower.js"

  import{buyerListService}from"@/api/buyer.js"
  const getDetailAndPicsAndComment=async()=>{
    const result1=await flowerDetailService(flowerStore.selectedFlowerId)
    flowerDetail.value=result1.data
  //  selectNum.value=result1.data.fnum
 // flowerDetail.value.fintroduction=flowerDetail.value.fintroduction.substring(4,flowerDetail.value.fintroduction.length-5)
    
    const result2=await flowerPicsService(flowerStore.selectedFlowerId)
    pics.value=result2.data

    const result3=await buyerListService()
    buyerList.value=result3.data

    const result4=await flowerCommentService(flowerStore.selectedFlowerId)
    flowerComments.value=result4.data



  for(let i=0;i<flowerComments.value.length;i++){
    let nowCommnet=flowerComments.value[i]
        nowCommnet.comtime= nowCommnet.comtime.substring(0,10)
    for(let j=0;j<buyerList.value.length;j++){
      if(nowCommnet.bid==buyerList.value[j].bid){
        nowCommnet.bname=buyerList.value[j].bname
      }
    } 
  }
}
  
  getDetailAndPicsAndComment()
  
  import{addCartService,addOrderService}from "@/api/trade.js"
  import { ElMessage,  } from 'element-plus';

  const Cart=async()=>{
   const cart=ref({
      'fid':flowerStore.selectedFlowerId,
      'carprice':flowerDetail.value.fprice*selectNum.value,
      'carnum':selectNum.value
    })
    console.log("cart.value",cart.value)
    const result=await addCartService(cart.value)
    ElMessage.success(result.data)
  }
  const Orders=async()=>{
    const result=await addOrderService('',flowerStore.selectedFlowerId,
    selectNum.value,flowerDetail.value.fprice*selectNum.value,ordtel.value,ordaddress.value)
    ElMessage.success(result.data)
  }



  const ordtel=ref(buyerStore.buyerInfo.btele)
  const ordaddress=ref(buyerStore.buyerInfo.baddress)
  const inputDisabled=ref(true)
  const getRecinfo=()=>{
     ordtel.value=buyerStore.buyerInfo.btele
   ordaddress.value=buyerStore.buyerInfo.baddress
  }
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-link @click="router.back();
        flowerStore.setSelectedFlowerId('')">
          <el-icon>
            <ArrowLeftBold />
          </el-icon>返回</el-link>
        <el-link type="warning" @click="drawerVisiable=true">查看评论</el-link>

      </el-header>
      <el-main class="main">
        <el-row>
          <el-col :span="14">
            <el-carousel arrow="always">
              <el-carousel-item class="carousel" v-for="item in pics" :key="item.id">
                <img :src=item.paddress class="image" />


              </el-carousel-item>
            </el-carousel>
          </el-col>
          <el-col :span="10">
            <el-card shadow="always" style="height: 55vh ;">
              <template #header>
                <div class="card-header">
                  <span style="color:#95d475 ;">{{flowerDetail.fname}}</span>
                  <span style="color: red;">￥{{flowerDetail.fprice}}</span>
                </div>

              </template>
              <table>
                <tr style="height: 40px">
                  <td width="60px">
                    描述:
                  </td>
                  <td width="150px" style="height: 50px;">
                    {{ flowerDetail.fintroduction }}
                  </td>
                </tr>
                <tr style="height: 40px">
                  <td width="60px">
                    库存:
                  </td>
                  <td width="150px">
                    {{ flowerDetail.fnum }}
                  </td>
                </tr>
              </table>
              <div class="card-buttons">
                <el-input-number v-model="selectNum" :min="1" :max="flowerDetail.fnum" @change="handleChange"
                  size="small" />
              </div>
              <div style="margin-top: 10px;">
                <el-button @click="Cart()" :disabled=buyerStore.buyerInfo.isSeller type="primary"
                  size="small">加入购物车</el-button>
                <el-button @click="dialogVisible=true" :disabled=buyerStore.buyerInfo.isSeller type="success"
                  size="small">立即购买</el-button>
              </div>
            </el-card>
          </el-col>

        </el-row>
        <el-dialog title="确认收货信息" v-model="dialogVisible" width="30%" style="background-color: #b3e19d;">
           <div class="dialog">
                <span>
                    <label >收货用户:</label><input type="text" disabled :placeholder="buyerStore.buyerInfo.bname">
                </span>
            </div>      
          <div class="dialog">
                    <span>
                    <label >联系方式:</label><input type="text" v-model="ordtel" :disabled="inputDisabled" >
                </span>
            </div>
            <div class="dialog">
                <span>
                    <label >收货地址:</label><input type="text" v-model="ordaddress" :disabled="inputDisabled">
                </span>
            </div>
                <template #footer>
                    <span class="dialog">
                      <el-button @click="dialogVisible = false; inputDisabled = true;getRecinfo()">取消</el-button>
                      <el-button @click="inputDisabled = false ">修改</el-button>
                        <el-button @click="Orders();dialogVisible = false">确定</el-button>
                    </span>
                </template>
        </el-dialog>
      </el-main>

    </el-container>
  </div>

  <el-drawer v-model="drawerVisiable" title="评论" direction="rtl" size="50%">
    <el-table :data="flowerComments" with="100%">
      <el-table-column label="评论人" prop="bname"></el-table-column>
      <el-table-column label="内容" prop="comments"></el-table-column>
      <el-table-column label="评分">
        <template #default="{ row }">
          <el-rate v-model="row.score" disabled show-score text-color="#ff9900" score-template="{row.score} points" />
        </template>

      </el-table-column>
      <el-table-column label="评论时间" prop="comtime"></el-table-column>
    </el-table>
  </el-drawer>
</template>



<style scoped>

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.carousel{
  height: 55vh;
}

.common-layout{
  width: 80%;
  margin-left: 10%;
  
}
.header{
  display: flex;
  background-color:#529b2e;
  height: 30px;
  /* border-radius:  20px 20px  ; */
  justify-content: space-between;
}
.image {
  width: 100%;
  display: block;
}

.dialog {
        display: flex;
       margin: 15px;
        justify-content: center;
    }
</style>