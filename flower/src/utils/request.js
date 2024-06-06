//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';

import { ElMessage } from 'element-plus'


//定义一个变量,记录公共的前缀  ,  baseURL
//原来的：const baseURL = 'http://localhost:8080';
const baseURL = '/api';//添加标识
//这里没有localhost。。，会自动拼接ajax所在的源。
//这里相当于：http://localhost:5173/api

const instance = axios.create({ baseURL })



//添加请求(request)拦截器
import { useTokenStore } from '@/stores/index.js';
instance.interceptors.request.use(
    //请求前
    (config)=>{
        const tokenStore=useTokenStore()
        if(tokenStore.token){
            config.headers.Authorization=tokenStore.token
        }
        //记得return
        return config;
    },
    //请求错误
    (err)=>{
        Promise.reject(err)
    }
)



//添加响应(response)拦截器
instance.interceptors.response.use(
    result=>{
        if(result.data.code===0){
            
            return result.data
        }
        ElMessage.error(result.data.message?result.data.message:"no")
        // alert(result.data.message?result.data.message:"no")
        //修改异步操作状态为失败
        return Promise.reject(result.data)
    },
    err=>{
        //处理未登录
        if(err.response.status===401){
            ElMessage.error('请先登录')
        }else{
            ElMessage.error('服务异常')
        }
       
        return Promise.reject(err);//异步的状态转化成失败的状态
        
        
    }
)


export default instance;