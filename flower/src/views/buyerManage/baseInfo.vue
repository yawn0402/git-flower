<script setup>
import { ref } from 'vue';
import { CircleCheck, Lock,Location ,Iphone} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const router=useRouter()

import {  useBuyerStore } from "@/stores/index.js"
const buyerStore = useBuyerStore()
const baseInfoModel=ref({
  bname: '',
  bsex:'',
  baddress:'',
  btele:'',
})
const getbaseInfo=()=>{
  baseInfoModel.value.bname=buyerStore.buyerInfo.bname
  baseInfoModel.value.bsex=buyerStore.buyerInfo.bsex
  baseInfoModel.value.baddress=buyerStore.buyerInfo.baddress
  baseInfoModel.value.btele=buyerStore.buyerInfo.btele

  // console.log("buyerStore.buyerInfo",buyerStore.buyerInfo)
  // console.log(baseInfoModel.value)
}
getbaseInfo()
const isEditing=ref(false)
const rules={
  btele: [
    { required: true, message: '不能为空哦', trigger: 'blur' },
    // { min: 11, max: 11, message: '请填写正确格式的电话', trigger: 'blur' }
  ],
  baddress: [
    { required: true, message: '不能为空哦', trigger: 'blur' },
  ],
    
}
import{buyerReBaseInfoService,buyerInfoService}from"@/api/buyer.js"
import { ElMessage } from 'element-plus';
const resetbaseInfo=async()=>{
  // console.log(baseInfoModel.value)
  const result1=await buyerReBaseInfoService(baseInfoModel.value)
  const result = await buyerInfoService()
  console.log(result.data)
  const newInfo = ref(
    {
      bid: result.data.bid,
      bname: result.data.bname,
      bsex: result.data.bsex ? result.data.bsex : "暂无",
      bavatar: result.data.bavatar,
      baddress: result.data.baddress ? result.data.baddress : "暂无",
      btele: result.data.btele?result.data.btele:"暂无",
      isSeller: result.data.bid === 1 ? true : false
    })
  buyerStore.removeuBuyerInfo()
  buyerStore.setBuyerInfo(newInfo.value)
  ElMessage.success("修改成功")
  getbaseInfo()
}


</script>

<template>
  <el-row class="login-page">
    <el-col :span="8" :offset="8" class="form">
      <el-form ref="form" size="middle" autocomplete="off" :model="baseInfoModel" :rules="rules">
        <el-form-item>
          <h1 class="title">基本信息</h1>
        </el-form-item>
        <el-form-item>
          <el-col :span="5">
            <label for="bname">用户名：</label>
          </el-col>

          <el-input class="input-msg" name="bname" :prefix-icon="Lock" disabled type="text"
            v-model="baseInfoModel.bname"></el-input>
        </el-form-item>
        <el-form-item prop="btele">
          <el-col :span="5">
            <label for="btelt">联系方式：</label>
          </el-col>
          <el-input class="input-msg" :disabled="!isEditing" name="btele" :prefix-icon="Iphone" type="text"
            v-model="baseInfoModel.btele"></el-input>
        </el-form-item>
        <el-form-item prop="baddress">
          <el-col :span="5">
            <label for="baddress">地址：</label>
          </el-col>
          <el-input class="input-msg" :disabled="!isEditing" name="baddress" :prefix-icon="Location" type="text"
            v-model="baseInfoModel.baddress"></el-input>
        </el-form-item>
        <el-form-item>
          <el-col :span="5">
            <label for="baddress">性别</label>
          </el-col>
          <el-radio-group v-model="baseInfoModel.bsex" :disabled="!isEditing">
            <el-radio value="男" label="男" size="large">男</el-radio>
            <el-radio value="女" label="女" size="large">女</el-radio>
            <el-radio value="暂无" label="暂无" size="large">暂无</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="!isEditing">
          <el-button class="button" type="warning" auto-insert-space @click="isEditing=true"> 修改</el-button>
        </el-form-item>

        <el-form-item v-show="isEditing">
          <div style="width: 100%; text-align: center;">
            <el-button style="width: 40%;" type="info" auto-insert-space @click="getbaseInfo();isEditing=false">
              取消</el-button>
            <el-button style="width: 40%;" type="primary" auto-insert-space @click="resetbaseInfo();isEditing=false">
              确定</el-button>
          </div>
        </el-form-item>

      </el-form>
    </el-col>
  </el-row>
</template>



<style scoped lang="scss">
  .login-page{
    height: 100%;
    background-color: #fff;
 
    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }
        .input-msg{
          width: 75%;
        }
        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
  }
  
</style>