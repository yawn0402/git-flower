<script setup>
    import { ref } from 'vue';
    import {
        Management,Promotion,UserFilled,User,Crop,
        EditPen,SwitchButton,CaretBottom,House,
        ShoppingCart,Memo,List,Star,DataAnalysis,
        Pointer,Collection
    } from '@element-plus/icons-vue'
   
    import {useBuyerStore,useTokenStore} from'@/stores/index.js'
    const buyerStore=useBuyerStore()

    const tokenStore=useTokenStore()
    

    import { useRouter } from 'vue-router';
    const router=useRouter()
    // console.log("buyerStore.buyerInfo2",buyerStore.buyerInfo)

    import {ElMessage,ElMessageBox} from 'element-plus'

const logout=()=>{
    ElMessageBox.confirm(
            '你确认退出登录码？',
            '温馨提示',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(async () => {
                tokenStore.removeToken()
                buyerStore.removeuBuyerInfo()
               router.push("/showFlower")
            })
            .catch(() => {
                //用户点击了取消
                ElMessage({
                    type: 'info',
                    message: '取消退出',
                })
            })
   
}

const dialogVisible=ref(false)

</script>


<template>
    <el-container class="layout-container">
        <el-header class="el-header">
            <div style="display: flex;align-items: center;">
                <img src="@/assets/logo2.png" alt="">
                <!-- <img src="https://big-event-ct.oss-cn-beijing.aliyuncs.com/ae9369e9-e773-4f07-a1ed-1973ac0d25f3.png" alt=""> -->
                <span style="margin-left: 10px; color: #f89898;">
                    翠微阁
                     <!-- MyGo!!!!! -->
                </span>

            </div>

            <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false" router :default-active="`/showFlower`">

                <el-menu-item index="/showFlower"><el-icon>
                        <House />
                    </el-icon>主页</el-menu-item>

                <el-menu-item index="/trade/cart" v-show="!buyerStore.buyerInfo.isSeller"><el-icon>
                        <ShoppingCart />
                    </el-icon>购物车</el-menu-item>
                <el-sub-menu v-if="!buyerStore.buyerInfo.isSeller">
                    <template #title><el-icon>
                            <Collection />
                        </el-icon>我的订单</template>
                    <el-menu-item index="/trade/order"><el-icon>
                            <Memo />
                        </el-icon>我的订单</el-menu-item>
                    <el-menu-item index="/trade/reorder"><el-icon>
                            <List />
                        </el-icon>售后记录</el-menu-item>
                    <el-menu-item index="/trade/comment"><el-icon>
                            <Star />
                        </el-icon>我的评价</el-menu-item>
                </el-sub-menu>
                <el-sub-menu v-else>
                    <template #title><el-icon>
                            <Collection />
                        </el-icon>订单管理</template>
                    <el-menu-item index="/trade/order"><el-icon>
                            <Memo />
                        </el-icon>所有订单</el-menu-item>
                    <el-menu-item index="/trade/reorder"><el-icon>
                            <List />
                        </el-icon>所有售后</el-menu-item>
                    <el-menu-item index="/trade/comment"><el-icon>
                            <Star />
                        </el-icon>所有评价</el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/purchase" v-show="buyerStore.buyerInfo.isSeller">
                    <el-icon>
                        <Pointer />
                    </el-icon>花卉管理</el-menu-item>
                <el-menu-item index="/inventory" v-show="buyerStore.buyerInfo.isSeller"><el-icon>
                        <DataAnalysis />
                    </el-icon>盘点</el-menu-item>
                <el-menu-item @click="buyerStore.buyerInfo.isSeller = !buyerStore.buyerInfo.isSeller;
                ">
                    change

                </el-menu-item>
            </el-menu>

            
            <span v-if="tokenStore.token==''">
                <el-link style="color: plum;" @click="router.push('/login');">
                去登陆</el-link>
            </span>
       
            <el-dropdown placement="bottom-end" v-else>
                <span class="el-dropdown__box">
                    <el-avatar :src="buyerStore.buyerInfo.bavatar" />
                    {{ buyerStore.buyerInfo.bname }}
                    <el-icon>
                        <CaretBottom />
                    </el-icon>

                </span>
                <!-- 一个带有 dropdown属性的template -->
                <template #dropdown>
                    <el-dropdown-menu>
                        <!-- 里面就是各个项 -->
                        <el-dropdown-item command="profile" :icon="User" @click="router.push('/buyerManage/baseInfo')">基本资料</el-dropdown-item>
                        <el-dropdown-item command="avatar" :icon="Crop" @click="router.push('/buyerManage/reAvatar')">更换头像</el-dropdown-item>
                        <el-dropdown-item command="password" :icon="EditPen" @click="router.push('/buyerManage/rePwd')">重置密码</el-dropdown-item>
                        <el-dropdown-item command="logout" :icon="SwitchButton"
                           @click="logout()" >退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </el-header>

        <el-main>
            <router-view>
            </router-view>
            <el-dialog  title="联系我们" v-model="dialogVisible" width="30%" style="background-color: pink;">
                <div class="dialog">
                    <span>
                    <label >邮箱:</label><input type="text" disabled placeholder="18273826745">
                </span>
            </div>
            <div class="dialog">
                <span>
                    <label >邮箱:</label><input type="text" disabled placeholder="cuiweige@outlook.com">
                </span>
            </div>
                <template #footer>
                    <span class="dialog">
                        <el-button @click="dialogVisible = false">确定</el-button>
                    </span>
                </template>
            </el-dialog>
        </el-main>
        <el-footer>
            <el-footer>
                <span>2024.6 Created by 翠微阁，</span>
                <el-link style="color: plum;" @click="dialogVisible = true">
                    联系我们</el-link>
            </el-footer>

        </el-footer>
    </el-container>
</template>

<style  lang="scss" scoped>
.layout-container {
    height: 100vh;
    .el-header {
    img{
        width: 60px;
    }
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin: 0;
        
        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active
            &:focus {
                outline: none;
             
            }
        }
    }
    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
        height: 30px;
    }
    .dialog {
        display: flex;
       margin: 15px;
        justify-content: center;
    }
}
</style>

