import Vue from 'vue';
import VueRouter from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";
import NewBlog from "../views/NewBlog";

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
      //path:'/main/:name',
      path:'/main',
      //props:true,
      name:'main',
      //跳转的组件
      component:Main
    },
    {
      path:'/newBlog',
      props:true,
      name:'newBlog',
      //跳转的组件
      component:NewBlog
    }
  ]
});
