<script setup>
import { createApp, ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter()
import { buyerLoginService, buyerInfoService,buyerSignupService } from "@/api/buyer.js"
import { useTokenStore, useBuyerStore } from "@/stores/index.js"
import { ElMessage } from 'element-plus'

const isSignin = ref(true)
const isSignup = ref(false)
const pinkboxStyle = ref({
    transform: 'translateX(0%)'
  })
  const  userdata = ref(
    {
      username: '',
      password: '',
      confirmPassword: '',
      errorMsg: ''
    }
  )


const handleSignup = () => {
  pinkboxStyle.value.transform = 'translateX(80%)';
  isSignin.value = false;
  isSignup.value = true;
}

const handleSignin = () => {
  pinkboxStyle.value.transform = 'translateX(0%)';
  isSignin.value = true;
  isSignup.value = false;
}

const createAccount=async()=>{
  const registerData = ref({
    buyername: userdata.value.username,
    password: userdata.value.password
  })
  let result = await buyerSignupService(registerData.value)
  return result.data
}
const submitSignup = () => {
  if (checkUsername(userdata.value.username) && checkPassword(userdata.value.password) && checkPwdconsist(userdata.value.password, userdata.value.confirmPassword)) {
    // 执行注册逻辑
    userdata.value.errorMsg = null;
    const msg= createAccount()
    alert('注册成功'); // 实际的注册逻辑
  }else if(checkPwdconsist(userdata.value.password, userdata.value.confirmPassword)){
    userdata.value.errorMsg = '用户名或密码不正确';
    // alert('用户名或密码填写错误');
    alert(userdata.value.errorMsg)
  } else {
    userdata.value.errorMsg = '两次密码不同';
    // alert('用户名或密码填写错误');
    alert(userdata.value.errorMsg)
  }
}
const submitSignin = () => {

}
const checkUsername = (username) => {
  if (username.length <= 8) {
    return true;
  } else {
    return false;
  }
}
const checkPassword = (password) => {
  if (password.length >= 3 && password.length <= 10 && /[a-zA-Z0-9]/.test(password)) {
    return true;
  } else {
    return false;
  }
}
const checkPwdconsist = (password, confirmPassword) => {
  if (password === confirmPassword) {
    return true;
  } else {
        return false;
      }
    
  }

const buyerStore = useBuyerStore()
const buyerInfoData = async () => {
  const result = await buyerInfoService()

  const newInfo = ref(
    {
      bid: result.data.bid,
      bname: result.data.bname,
      bsex: result.data.sex ? result.data.sex : "未知",
      bavatar: result.data.bavatar,
      baddress: result.data.baddress ? result.data.baddress : "暂无",
      isSeller: result.data.bid === 1 ? true : false
    })
  buyerStore.removeuBuyerInfo()
  buyerStore.setBuyerInfo(newInfo.value)
  console.log("buyerStore.buyerInfo1", buyerStore.buyerInfo)
  return result.data
}

const tokenStore = useTokenStore();
const login = async () => {
  const registerData = ref({
    buyername: userdata.value.username,
    password: userdata.value.password
  })
  // console.log(registerData.value)
  let result = await buyerLoginService(registerData.value)
  tokenStore.setToken(result.data)
  // console.log(result.data)
  ElMessage.success(result.data.message ? result.data.message : 'susess')
  buyerInfoData()

  router.push("/")

}



</script>


<template>
    <div class="container">
      <div class="welcome">
        <div class="pinkbox" :style="pinkboxStyle">
            <div v-if="isSignup" class="signup">
                <h1>注册</h1>
                <form class="more-padding" autocomplete="off" @submit.prevent="submitSignup">
                <input type="text" placeholder="用户名 不多于8位" v-model="userdata.username">
                <input type="password" placeholder="密码 6-10位且包含字母" v-model="userdata.password">
                <input type="password" placeholder="确认密码" v-model="userdata.confirmPassword">
                <span v-if="errorMsg" class="error">{{ userdata.errorMsg }}</span>
                <button class="button submit" >创建账户</button>
                </form>
            </div>

            <div v-else class="signin">
                <h1>登录</h1>
                <form class="more-padding" autocomplete="off" @submit.prevent="submitSignin">
                <input type="text" placeholder="用户名" v-model="userdata.username">
                <input type="password" placeholder="密码" v-model="userdata.password">
                <div class="checkbox">
                    <input type="checkbox" id="remember" /><label for="remember">记住密码</label>
                </div>
                <button class="button submit" @click="login()">登录</button>
                </form>
            </div>
        </div>
  
        <div class="leftbox">
          <h2 class="title"><span>花开四季</span><br>爱永不熄</h2>
          <p class="desc">专业花艺，打造您的<span>专属花礼</span></p>
          <img class="flower smaller" src="https://hbimg.huabanimg.com/c09305167a883e60179a45374df73252304001359acca-W3qbYm_fw658/format/webp" />
          <p class="account">已拥有账号?</p>
          <button class="button" @click="handleSignin" id="signin">登录</button>
        </div>
  
        <div class="rightbox">
          <h2 class="title"><span>花开四季</span><br>爱永不熄</h2>
          <p class="desc">专业花艺，打造您的<span>专属花礼</span></p>
          <img class="flower" src="https://hbimg.huabanimg.com/b28be92c8198975a74ad656eba00b352c9b9e589819af-lDXUAS_fw658/format/webp" />
          <p class="account">还未拥有账号?</p>
          <button class="button" @click="handleSignup" id="signup">注册</button>
        </div>
      </div>
    </div>
  </template>
          

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Open+Sans:300,400|Lora");


body {
  background: #cbc0d3;
}

/* 容器的样式 */
.container {
  margin: auto;
  width: 650px;
  height: 550px;
  position: relative;
  
}

.welcome {
  background: #f6f6f6;
  width: 650px;
  height: 415px;
  position: absolute;
  top: 25%;
  border-radius: 5px;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
}

.pinkbox {
  position: absolute;
  top: -10%;
  left: 5%;
  background: #eac7cc;
  width: 320px;
  height: 500px;
  border-radius: 5px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  transition: all 0.5s ease-in-out;
  z-index: 2;
}

.nodisplay {
  display: none;
  transition: all 0.5s ease;
}

.leftbox, .rightbox {
  position: absolute;
  width: 50%;
  transition: 1s all ease;
}

.leftbox {
  left: -2%;
}

.rightbox {
  right: -2%;
}

/* 字体和按钮的样式 */
h1 {
  font-family: "Open Sans", sans-serif;
  text-align: center;
  margin-top: 95px;
  text-transform: uppercase;
  color: #f6f6f6;
  font-size: 2em;
  letter-spacing: 8px;
}

.title {
  font-family: "Lora", serif;
  color: #8e9aaf;
  font-size: 1.8em;
  line-height: 1.1em;
  letter-spacing: 3px;
  text-align: center;
  font-weight: 300;
  margin-top: 20%;
}

.desc {
  margin-top: -8px;
}

.account {
  margin-top: 45%;
  font-size: 10px;
}

p {
  font-family: "Open Sans", sans-serif;
  font-size: 0.7em;
  letter-spacing: 2px;
  color: #8e9aaf;
  text-align: center;
}

span {
  color: #eac7cc;
}

.flower {
  position: absolute;
  width: 150px;
  height: 150px;
  top: 45%;
  left: 27%;
  opacity: 0.8;
}

.smaller {
  width: 130px;
  height: 130px;
  top: 48%;
  left: 30%;
  opacity: 0.9;
}

button {
  padding: 12px;
  font-family: "Open Sans", sans-serif;
  text-transform: uppercase;
  letter-spacing: 3px;
  font-size: 11px;
  border-radius: 10px;
  margin: auto;
  outline: none;
  display: block;
}

button:hover {
  background: #eac7cc;
  color: #f6f6f6;
  transition: background-color 1s ease-out;
}

.button {
  margin-top: 3%;
  background: #f6f6f6;
  color: #ce7d88;
  border: solid 1px #eac7cc;
}

/* 表单样式 */
form {
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 7px;
}

.more-padding {
  padding-top: 35px;
}

.more-padding input {
  padding: 12px;
}

.more-padding .sumbit {
  margin-top: 45px;
}

.sumbit {
  margin-top: 25px;
  padding: 12px;
  border-color: #ce7d88;
}

.sumbit:hover {
  background: #cbc0d3;
  border-color: #bfb1c9;
}

input {
  background: #eac7cc;
  width: 65%;
  color: #ce7d88;
  border: none;
  border-bottom: 1px solid rgba(246, 246, 246, 0.5);
  padding: 9px;
  font-weight: 100;
}

input::placeholder {
  color: #f6f6f6;
  letter-spacing: 2px;
  font-size: 1.0em;
  font-weight: 100;
}

input:focus {
  color: #ce7d88;
  outline: none;
  border-bottom: 1.2px solid rgba(206, 125, 136, 0.7);
  font-size: 1.0em;
  transition: 0.8s all ease;
}

input:focus::placeholder {
  opacity: 0;
}

label {
  font-family: "Open Sans", sans-serif;
  color: #ce7d88;
  font-size: 0.8em;
  letter-spacing: 1px;
}

.checkbox {
  display: inline;
  white-space: nowrap;
  position: relative;
  left: -52px;
  top: 25px;
  margin-bottom: 30px;
}

input[type=checkbox] {
  width: 15px;
  background: #ce7d88;
}

.checkbox input[type=checkbox]:checked + label {
  color: #ce7d88;
  transition: 0.5s all ease;
}
</style>