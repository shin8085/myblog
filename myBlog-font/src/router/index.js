import Vue from 'vue';
import VueRouter from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";

//安装路由
Vue.use(VueRouter)


//配置导出路由
export default new VueRouter({
  mode:"history",
  routes:[
    {
      path:'/',
      component: Login
    },
    {
      //路由路径
      path:'/main',
      name:'main',
      //跳转的组件
      component:Main
    }
  ]
});
