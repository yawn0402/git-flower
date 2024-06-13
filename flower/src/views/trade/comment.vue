<script setup>
import { ref } from 'vue';
import{Edit,Delete,ShoppingCartFull} from '@element-plus/icons-vue'
//文章列表数据模型

const total=ref(20)
const pageNum=ref(1)
const pageSize=ref(4)
const commentsList=ref([
    {
      bname:"s",
    fname:"soyo",
    score:3,
    comments:"234cd",
    comtime:"2024-05-30 19:55:38"

    },
    
])
const flowerList=ref('')
const buyerList=ref('')

import{flowerListService}from"@/api/flower.js"
import{buyerListService}from"@/api/buyer.js"
import{commentListService,commentDelteService,}from"@/api/comment.js"
import{}from "@/api/trade.js"
import { ElMessage } from 'element-plus';
import{useBuyerStore} from'@/stores/index.js'

const buyerStore=useBuyerStore()
const getCommentList=async()=>{
  let bid=''
  if(!buyerStore.buyerInfo.isSeller){
    bid=buyerStore.buyerInfo.bid
  }
  const result=await commentListService(pageNum.value,bid)
  // console.log(result.data)
  commentsList.value=result.data.items
  total.value=result.data.total

  const result1=await flowerListService()
  flowerList.value=result1.data.items

  const result2=await buyerListService()
  buyerList.value=result2.data

  for(let i=0;i<commentsList.value.length;i++){
    let nowcom=commentsList.value[i]
    for(let j=0;j<flowerList.value.length;j++){
      if(nowcom.fid==flowerList.value[j].fid){
        nowcom.fname=flowerList.value[j].fname
      }
    }
    for(let k=0;k<buyerList.value.length;k++){
      if(nowcom.bid==buyerList.value[k].bid){
        nowcom.bname=buyerList.value[k].bname
      }
    }
  }
  for(let i=0;i<commentsList.value.length;i++){
    commentsList.value[i].comtime=commentsList.value[i].comtime.substring(0,10)
  }
}

getCommentList()

  const deleteComment=async(comid)=>{
   const result=await commentDelteService(comid)
   ElMessage.success("删除成功")
   getCommentList()
  }

</script>

<template>
 <el-card class="page-container ">
   <el-table class="dataTable" :data="commentsList" style="width: 100% ">
    <el-table-column label="用户" width="150" prop="bname"></el-table-column> 
     <el-table-column label="花卉" width="150" prop="fname"></el-table-column> 
     <el-table-column label="评分" prop="score"> 
      <template #default="{ row }">
          <el-rate v-model="row.score" disabled show-score text-color="#ff9900" />
        </template></el-table-column>
     <el-table-column label="评价" prop="comments"></el-table-column>
     <el-table-column label="评论时间" width="200" prop="comtime" ></el-table-column>
     <el-table-column label="操作" width="100">
       <template #default="{ row }">
         <el-button  @click="deleteComment(row.comid)" :icon="Delete" circle plain type="danger"></el-button>
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
    @current-change="getCommentList()"
    :page-size=pageSize
    v-model:current-page="pageNum"
    style="margin-top: 20px; justify-content: center"
  />

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
</style>