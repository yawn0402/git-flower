<script setup>
import { ref } from 'vue'

const currentDate = ref(new Date())


import{useFlowerStore} from'@/stores/index.js'
const FlowerStore=useFlowerStore()


import { useRouter } from 'vue-router';
const router=useRouter()
const goDetail=(id)=>{
  console.log(id)
  FlowerStore.setSelectedFlowerId(id)
  console.log(FlowerStore.selectedFlowerId);
  router.push('/flowerDetail')

}

 
</script>

<template>
  <div class="background">
    <el-row style="display: flex; justify-content: center;">
      <el-form inline>
        <el-form-item>
          <!-- <img src="@/assets/logo2.png" alt="" style="width: 50px;"> -->
          <el-input style="width: 350px;"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" >搜索</el-button>
          <el-button >重置</el-button>
      </el-form-item>
    </el-form>
    </el-row>
    <el-row >
    <el-col
      v-for="o in 8"
      :key="o"
      :span="5"
      :offset="(o%4)==1?2:0"
    >
      <el-card :body-style="{ padding: '0px' }" style="margin: 10px;">
        <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image"
        />
        <div style="padding: 13px">
          <span>Yummy hamburger</span>
          <div class="bottom">
            <time class="time">{{ currentDate }}</time>
            <el-button text class="button" @click="goDetail(o)">Operating</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
  </div>
</template>



<style>
.time {
  font-size: 12px;
  color: #999;
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
}

.image {
  width: 100%;
  display: block;
}
</style>
