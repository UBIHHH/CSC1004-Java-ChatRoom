//路由
import VueRouter from "vue-router"
import Login from '../views/login/LoginPage.vue'
import Register from '../views/register/RegisterPage.vue'
import Chat from '../views/home/ChatPage.vue'
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
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/chat',
            component: Chat
        }
    ]
})


router.beforeEach((to, from, next) => {
    let isAuthenticated = !!sessionStorage.getItem('userInfo')

    if (to.path !== '/login' && to.path !== '/register' && !isAuthenticated) {
        next({ path: '/login' })
        Message({
            message: 'Please Login first！',
            type: "warning",
        });
    } else next()
})

export default router;