// 此文件专门负责项目的路由

import VueRouter from "vue-router"

// 引入组件
import Login from '../views/login/LoginPage.vue'
import Register from '../views/register/RegisterPage.vue'
import Home from '../views/home/HomePage.vue'
import Chat from '../components/Room.vue'
import { Message } from "element-ui";

// 创建并暴露一个路由器
const router = new VueRouter({
    mode: 'history',    // 路由模式，该模式不会在地址中显示井号#
    routes: [
        {
            path: '/',          // 路径
            redirect: '/login'  // 重定向
        },
        {
            path: '/login',     // 路径
            component: Login    // 跳转到的组件
        },
        {
            path: '/register',     // 路径
            component: Register    // 跳转到的组件
        },
        {
            path: '/home',     // 路径
            component: Home    // 跳转到的组件
        },
        {
            path: '/chat',     // 路径
            component: Chat    // 跳转到的组件
        }
    ]
})


router.beforeEach((to, from, next) => {
    let isAuthenticated = !!sessionStorage.getItem('userInfo')

    if (to.path !== '/login' && to.path !== '/register' && !isAuthenticated) {
        next({ path: '/login' })
        Message({
            message: '请先登录！',
            type: "warning",
        });
    } else next()
})

export default router;