<script setup>
import { ref } from 'vue';
import { CircleCheck, Lock } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const router=useRouter()
const newData=ref({
  newPwd:'',
  confirmPwd:''
}
)

const rules={
  newPwd: [
    { required: true, message: '不能为空哦', trigger: 'blur' },
    { min: 3, max: 16, message: '长度 3-16', trigger: 'blur' }
  ],
  confirmPwd: [
    { required: true, message: '不能为空哦', trigger: 'blur' },
    { min: 3, max: 16, message: '长度 3-16', trigger: 'blur' }
  ],
    
}
import{buyerRePwdService}from"@/api/buyer.js"
import { ElMessage } from 'element-plus';
const resetPwd=async()=>{
  if(newData.value.confirmPwd===newData.value.newPwd){
    console.log(newData.value.newPwd)
    const result=await buyerRePwdService(newData.value.newPwd)
    ElMessage.success("修改成功，请重新登录")
    router.push('/login')
  }else{
    ElMessage.error("两次密码不同")
  }
}
</script>

<template>
  <el-row class="login-page">
      <el-col :span="6" :offset="9" class="form" >
        <el-form ref="form" size="large" autocomplete="off"  :model="newData" :rules="rules" >
          <el-form-item >
            <h1 class="title">重置密码</h1>
          </el-form-item>
          <el-form-item prop="newPwd" :rules="rules.newPwd">
            <el-input :prefix-icon="Lock" name="newPwd" type="password" placeholder="请输入密码" v-model="newData.newPwd"></el-input>
          </el-form-item>
          <el-form-item  prop="confirmPwd" :rules="rules.confirmPwd">
            <el-input name="confirmPwd" :prefix-icon="CircleCheck" type="password" placeholder="请再次输入密码" v-model="newData.confirmPwd"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button class="button" type="primary" auto-insert-space @click="resetPwd()"> 确定</el-button>
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
        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
  }
  
</style>