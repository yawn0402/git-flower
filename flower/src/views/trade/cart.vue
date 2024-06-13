<script setup>
import { ref } from 'vue';
import{Edit,Delete,ShoppingCartFull} from '@element-plus/icons-vue'
import{useBuyerStore} from'@/stores/index.js'

  const buyerStore=useBuyerStore()

const carts=ref([
    {
        fname:'soyo',
        fcover:'',
        carnum:3,
        carprice:42,

    }
])
const total=ref(20)
const pageNum=ref(1)
const pageSize=ref(3)
const flowerList=ref('')
const dialogVisible = ref(false)
import{cartListService}from"@/api/trade.js"

import{flowerListService}from"@/api/flower.js"
const getCartList=async()=>{
  const result=await cartListService(pageNum.value)
  total.value=result.data.total
  carts.value=result.data.items

  const result1=await flowerListService()
  flowerList.value=result1.data.items
  for(let i=0;i<carts.value.length;i++){
    let nowcart=carts.value[i]
    for(let j=0;j<flowerList.value.length;j++){
      if(nowcart.fid==flowerList.value[j].fid){
        nowcart.fname=flowerList.value[j].fname
        nowcart.fcover=flowerList.value[j].fcover
      }
    }
  }
  for(let i=0;i<carts.value.length;i++){
    carts.value[i].carprice=carts.value[i].carprice.toFixed(1)
  }
}
getCartList()

  import{addOrderService,cartDeleteService}from "@/api/trade.js"
  import { ElMessage } from 'element-plus';
  const buyit=async(carid)=>{
    console.log(carid)
    const result= await addOrderService(carid,'','','')
    getCartList()
    ElMessage.success("购买成功")
  }

  const deleteCart=async(carid)=>{
    console.log(carid)
    const result= await cartDeleteService(carid)
    getCartList()
    ElMessage.success("删除成功")
  }
  const selectedCarid=ref('')
</script>

<template>
 <el-card class="page-container ">
   <el-table class="dataTable" :data="carts" style="width: 100% ">
     <el-table-column label="花卉" width="250" prop="fname"></el-table-column>
     <el-table-column label="图片" width="300" >
        <template #default="{ row }" >
        <img 
          :src=row.fcover
          class="image"
        />
        </template>
     </el-table-column>
     <el-table-column label="数量" prop="carnum"> </el-table-column>
     <el-table-column label="总价" prop="carprice"></el-table-column>
     <el-table-column label="操作" width="100">
       <template #default="{ row }">
         <el-button @click="dialogVisible=true;selectedCarid=row.carid" :icon="ShoppingCartFull" circle plain type="primary" ></el-button>
         <el-button  @click="deleteCart(row.carid)" :icon="Delete" circle plain type="danger"></el-button>
       </template>
     </el-table-column>
     <template #empty>
       <el-empty description="没有数据" />
     </template>
   </el-table>
   <!-- 分页条 -->
   
  <el-pagination
    small
    background
   layout="jumper, total,  prev, pager, next"
    :total="total"
    @current-change="getCartList()"
    :page-size=pageSize
    v-model:current-page="pageNum"
    style="margin-top: 20px; justify-content: center"
  />

 </el-card>
 <el-dialog  title="确认收货信息" v-model="dialogVisible" width="30%" style="background-color: #b3e19d;">
          <div class="dialog">
                <span>
                    <label >收货用户:</label><input type="text" disabled :placeholder="buyerStore.buyerInfo.bname">
                </span>
            </div>      
          <div class="dialog">
                    <span>
                    <label >联系方式:</label><input type="text" disabled  :placeholder="buyerStore.buyerInfo.btele">
                </span>
            </div>
            <div class="dialog">
                <span>
                    <label >收货地址:</label><input type="text" disabled :placeholder="buyerStore.buyerInfo.baddress">
                </span>
            </div>
                <template #footer>
                    <span class="dialog">
                      <el-button @click="dialogVisible = false">取消</el-button>
                        <el-button @click="buyit(selectedCarid);dialogVisible = false">确定</el-button>
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
/* 抽屉样式 */
.avatar-uploader {
 :deep() {
   .avatar {
     width: 178px;
     height: 178px;
     display: block;
   }

   .el-upload {
     border: 1px dashed var(--el-border-color);
     border-radius: 6px;
     cursor: pointer;
     position: relative;
     overflow: hidden;
     transition: var(--el-transition-duration-fast);
   }

   .el-upload:hover {
     border-color: var(--el-color-primary);
   }

   .el-icon.avatar-uploader-icon {
     font-size: 28px;
     color: #8c939d;
     width: 178px;
     height: 178px;
     text-align: center;
   }
 }
}

.editor {
 width: 100%;

 :deep(.ql-editor) {
   min-height: 200px;
 }
}
.dialog {
        display: flex;
       margin: 15px;
        justify-content: center;
    }
</style>