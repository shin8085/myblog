import Vue from 'vue';
import VueRouter from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";
import EditBlog from "../views/EditBlog";
import ShowBlog from "../views/ShowBlog";

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
      component:Main,
      meta:{
        requireAuth:true
      }
    },
    {
      path:'/editBlog/:id',
      props:true,
      name:'editBlog',
      //跳转的组件
      component:EditBlog,
      meta:{
        requireAuth:true
      }
    },
    {
      path:'/showBlog/:id',
      props:true,
      name:'showBlog',
      //跳转的组件
      component:ShowBlog,
      meta:{
        requireAuth:true
      }
    },
  ]
});
