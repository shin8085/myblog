// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router' //自动扫描
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Axios from "axios";

Vue.config.productionTip = false
Vue.prototype.$axios=Axios

Vue.use(ElementUI);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  //配置路由
  router,
  components: { App },
  template: '<App/>',
})
