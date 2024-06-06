import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from'element-plus'
import "element-plus/dist/index.css"
import locale from 'element-plus/dist/locale/zh-cn.js'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'

const persist=createPersistedState();
const pinia=createPinia();
pinia.use(persist)
const app=createApp(App);
app.use(pinia)
app.use(router)
app.use(ElementPlus,{locale});
app.mount('#app')
