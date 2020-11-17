<template>
<div>
  <el-container direction="vertical">
    <MainHeader/>
    <el-main>
      <div class="block">
        <el-timeline>
          <el-timeline-item v-for="blog in blogs" :key="blog.id" :timestamp="getDate(blog.datetime)" placement="top">
            <el-card @click.native="toShowBlog(blog.id)" style="cursor: pointer;">
              <h4>{{blog.title}}</h4>
            </el-card>
          </el-timeline-item>

        </el-timeline>
      </div>
    </el-main>
  </el-container>
</div>
</template>

<script>
import MainHeader from "../components/MainHeader";
export default {
  name: "Main",
  components: {MainHeader},
  data(){
    return{
      h_height:"150px",
      blogs:[]
    }
  },
  methods:{
    getDate:function(timestamp){
      let now = new Date(timestamp),
      y = now.getFullYear(),
      m = now.getMonth() + 1,
      d = now.getDate();
      return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
    },
    toShowBlog:function(id){
      this.$router.push({
        name:'showBlog',
        params:{
          id:id,
        }
      });
    }
  },
  mounted() {
    this.$axios.post('/api/blog/getBlogsByUserName',{name:sessionStorage.getItem("userName"),password:null}).then(res=>{
      this.blogs=res.data.data
    })
  }
}
</script>

<style scoped>

</style>
