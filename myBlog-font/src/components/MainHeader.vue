<template>
  <el-header :style="{height: header_height}">
    <div>
      <div class="demo-basic--circle">
        <div class="block">
          <el-avatar :size="50" :src="circleUrl"></el-avatar>
        </div>
      </div>
      <span @click="this.logout" style="cursor: pointer; color: red">退出登入</span>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click="toMain">首页</el-menu-item>
        <el-button type="primary" style="float: right" @click="toNewBlog">新建</el-button>
      </el-menu>
    </div>
  </el-header>
</template>

<script>

export default {
  name: "MainHeader",
  data() {
    return {
      header_height:"150px",
      activeIndex: '1',
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      sizeList: ["large", "medium", "small"]
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      //console.log(key, keyPath);
    },
    toMain(){
      this.$router.push("/main");
    },
    toNewBlog(){
      this.$router.push({
        name:'editBlog',
        params:{
          id:"new",
        }
      });
    },
    logout(){
      this.$axios.get('/api/logout').then(res=>{
        this.$router.push('/');
      })
    }
  }
}
</script>

<style scoped>

</style>
