import { createRouter, createWebHistory } from 'vue-router'

import Layout from '@/views/Layout.vue'
import showFlower from '@/views/flowerManage/showFlower.vue'
import cart from '@/views/trade/cart.vue'
import reorder from '@/views/trade/reorder.vue'
import order from '@/views/trade/order.vue'
import comment from '../views/trade/comment.vue'
import inventory from'@/views/inventory.vue'
import flowerDetail from"@/views/flowerManage/flowerDetail.vue"
import purchaseHistory from"@/views/flowerManage/purchaseHistory.vue"
import lossHistory from"@/views/flowerManage/lossHistory.vue"
import purchase from'@/views/pruchase.vue'
import LoginSignup from'@/views/LoginSignup.vue'
import baseInfo from"@/views/buyerManage/baseInfo.vue"
import reAvatar from"@/views/buyerManage/reAvatar.vue"
import rePwd from"@/views/buyerManage/rePwd.vue"


const routes = [
    {
        path: '/login',
        component:LoginSignup,
        
    },
    {

        name: 'layout',
        path: '/',
        component:Layout,
        redirect: '/showFlower',
        children: [
            {
                path:'/buyerManage/rePwd',
                component:rePwd
            },
            {
                path:'/buyerManage/reAvatar',
                component:reAvatar
            },
            {
                path:'/buyerManage/baseInfo',
                component:baseInfo
            },
            {
                path: '/showFlower',
                component: showFlower,
            },
            {
                path: '/trade/cart',
                component: cart,
            },
            {
                path: '/trade/reorder',
                component: reorder,
            },
            {
                path: '/trade/order',
                component: order,
            },
            {
                path: '/trade/comment',
                component: comment,
            },
            {
                path: '/inventory',
                component: inventory,
            },
            {
                path: '/flowerDetail',
                component: flowerDetail,
            },
            {
                path: '/purchase',
                component: purchase,
            },
            {
                path: '/purchaseHistory',
                component: purchaseHistory,
            },
            {
                path: '/lossHistory',
                component: lossHistory,
            },
        ]
    },

]






const router = createRouter({
    history: createWebHistory(),
    routes: routes
})




export default router