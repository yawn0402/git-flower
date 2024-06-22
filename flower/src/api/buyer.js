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
export const buyerInfoService=()=>{
    return request.get("/buyer/buyerInfo")
}

export const buyerRePwdService=(newPwd)=>{
    const params=new URLSearchParams;
    params.append('newPwd',newPwd)
    return request.put("/buyer/rePwd",params)
}

//修改头像
export const userAvatarUpdateService = (avatarUrl)=>{
    const params = new URLSearchParams();
    params.append('avatarUrl',avatarUrl)
    return request.patch('/buyer/avatar',params)
}



export const buyerReBaseInfoService=(buyerInfo)=>{
    
    return request.put("/buyer/reBaseInfo",buyerInfo)
}



export const buyerListService=()=>{
    
    return request.get("/buyer/list")
}
