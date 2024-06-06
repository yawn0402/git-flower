import request from "@/utils/request"

//注册
export const buyerSignupService=(registerData)=>{
    const params=new URLSearchParams;
    for(let key in registerData){
        params.append(key,registerData[key])
    }
    return request.post("/buyer/register",params);
}

//登录接口
export const buyerLoginService=(registerData)=>{
    //格式转换
    const params=new URLSearchParams;
    for(let key in registerData){
        params.append(key,registerData[key])
    }
    return request.post("/buyer/login",params);
}
//买家信息
export const buyerInfoService=(registerData)=>{
    return request.get("/buyer/buyerInfo")
}