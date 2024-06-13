import { defineStore } from "pinia";
import { ref } from "vue";


export const useTokenStore=defineStore('token',
    ()=>{
        const token=ref('')

        const setToken=(newToken)=>{
            token.value=newToken
        }

        const removeToken=(newToken)=>{
            token.value=''
        }
        return{
            token,setToken,removeToken
        }
    },
    {
        persist:true
    }
)


export const useBuyerStore=defineStore('user',
    ()=>{
        const buyerInfo=ref({
        bid: '',
        bname: '',
       	bsex:'',
        bavatar:'',
        baddress:'',
        btele:'',
        isSeller:false
        })
     
        const removeuBuyerInfo=()=>{
            buyerInfo.value={
                bid: '',
                bname: '',
                   bsex:'',
                bavatar:'',
                baddress:'',
                isSeller:false
                }
        }
        const setBuyerInfo=(newInfo)=>{
            buyerInfo.value=newInfo
        }

        const setAvatar=(newAvatar)=>{
            buyerInfo.value.bavatar=newAvatar
        }
        return{
            buyerInfo,removeuBuyerInfo,setBuyerInfo,setAvatar
        }
    },
    {
        persist:true
    }
)


export const useFlowerStore=defineStore('flower',
    ()=>{
      
        const selectedFlowerId=ref('')
        const setSelectedFlowerId=(newSelectedFlowerId)=>{
            selectedFlowerId.value=newSelectedFlowerId
        }
        const removeSelectedFlowerId=()=>{
            selectedFlowerId.value=''
        }
        return{
            selectedFlowerId,setSelectedFlowerId,removeSelectedFlowerId
        }
    },
    {
        persist:true
    }
)