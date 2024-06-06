import { createRouter, createWebHistory } from 'vue-router'

import Layout from '@/views/Layout.vue'
import showFlower from '@/views/flowerManage/showFlower.vue'
import cart from '@/views/trade/cart.vue'
import reorder from '@/views/trade/reorder.vue'
import order from '@/views/trade/order.vue'
import comment from '../views/trade/comment.vue'
import inventory from'@/views/inventory.vue'
import flowerDetail from"@/views/flowerManage/flowerDetail.vue"
import purchase from'@/views/pruchase.vue'
import LoginSignup from'@/views/LoginSignup.vue'

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
        ]
    },

]






const router = createRouter({
    history: createWebHistory(),
    routes: routes
})




export default router