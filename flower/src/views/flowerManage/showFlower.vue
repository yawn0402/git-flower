<script setup>
import { ref } from 'vue'

const currentDate = ref(new Date())

import{useFlowerStore,useTokenStore} from'@/stores/index.js'
const FlowerStore=useFlowerStore()
const tokenStore=useTokenStore()

import { useRouter } from 'vue-router';
const router=useRouter()
const goDetail=(id)=>{
  if(tokenStore.token===''){
    ElMessage.error("请先登录")
  }else{
    console.log(id)
  FlowerStore.setSelectedFlowerId(id)
  console.log(FlowerStore.selectedFlowerId);
  router.push('/flowerDetail')
  }
}
const flowerList=ref([
  {
    fid:2,
    fname:'f2',
    fprice:25,
    fcover:''
    // https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png
  },
  {
    fid:3,
    fname:'f3',
    fprice:27,
    fcover:''
  },
  {
    fid:1,
    fname:'f1',
    fprice:23,
    fcover:''
  },
])
 const flowerNum=ref(3)
 import{flowerListService,flowerSearchService}from"@/api/flower.js"
import { ElMessage } from 'element-plus';
 const getFlowerList=async()=>{
  const result =await flowerListService()
  flowerList.value=result.data.items
  flowerNum.value=result.data.flowerNum
 }
getFlowerList()
const toSearch=ref('')

const search=async()=>{
  const result =await flowerSearchService(toSearch.value)
  flowerList.value=result.data.items
  flowerNum.value=result.data.flowerNum
  ElMessage.success("搜索成功")
  console.log(result.data)
}
</script>

<template>
  <div class="background">
    <el-row style="display: flex; justify-content: center;">
      <el-form inline>
        <el-form-item>
          <!-- <img src="@/assets/logo2.png" alt="" style="width: 50px;"> -->
          <el-input style="width: 350px;" v-model="toSearch"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="search()">搜索</el-button>
          <el-button @click="getFlowerList();toSearch=''" >重置</el-button>
      </el-form-item>
    </el-form>
    </el-row>
    <el-row >
    <el-col
      v-for="num in flowerNum"
      :key="num"
      :span="5"
      :offset="(num%4)==1?2:0"
    >
      <el-card :body-style="{ padding: '0px' }" style="margin: 10px;">
        <!-- <span>
          {{ num }}
        </span> -->
        <img
          :src=flowerList[num-1].fcover
          class="image"
        />
        <div style="padding: 13px">
          <span>{{flowerList[num-1].fname}}</span>
          <div class="bottom">
            <h1 class="price">￥{{ flowerList[num-1].fprice }}</h1>
            <el-button  class="button" @click="goDetail(flowerList[num-1].fid)">查看详情</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
  </div>
</template>



<style scoped>
.price {
  font-size: 22px;
  color: rgb(241, 84, 84);
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
  background-color:#fab6b6 ; 
  color: #fef0f0;
}

.image {
  width: 100%;
  height: 200px;
  display: block;
}
</style>
