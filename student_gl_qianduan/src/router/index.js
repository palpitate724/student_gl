import { createRouter,createWebHashHistory } from 'vue-router'
import { compile } from "vue";

import login from "../components/login.vue"
import signup from "../components/signup.vue"

import toulan from '../components/zujian/toulan.vue';
import mulu from "../components/zujian/mulu.vue"
import zhuye from '../components/zhuye.vue'


const routes=[
    {
        path : '/',
        component : login
    },
    {
        path : '/login',
        name : '登录',
        component : login
    },
    {
        path : '/signup',
        name : '注册',
        component : signup
    },
    {
        path : '/zhuye',
        name : '主页',
        component : zhuye
    },
]

export default createRouter({
    history:createWebHashHistory(),
    routes
})