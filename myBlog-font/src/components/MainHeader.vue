<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1" @click="toMain">首页</el-menu-item>
      <el-button type="primary" style="float: right" @click="toNewBlog">新建</el-button>
    </el-menu>
  </div>
</template>

<script>

export default {
  name: "MainHeader",
  data() {
    return {
      activeIndex: '1',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    toMain(){
      this.$router.push("/main");
    },
    toNewBlog(){
      this.$router.push("/newBlog");
    }
  }
}
</script>

<style scoped>

</style>
