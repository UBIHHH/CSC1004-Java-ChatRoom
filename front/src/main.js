import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.prototype.$http = axios; //修改内部的$http为axios  $http.get("")
// 关闭 Vue 的生产提示
Vue.config.productionTip = false

Vue.use(ElementUI) // element ui 插件
Vue.use(VueRouter) // 路由插件
Vue.use(VueAxios, axios) // 使用 axios 插件

// 创建 Vue 实例对象
new Vue({
  render: h => h(App),
  router
}).$mount('#app')