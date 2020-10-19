<template>
<div>
  <el-container>
    <el-header>
      <MainHeader/>
    </el-header>
    <el-main>
      <div class="block">
        <el-timeline>
          <el-timeline-item v-for="blog in blogs" :key="blog.id" :timestamp="getDate(blog.datetime)" placement="top">
            <el-card>
              <h4>{{blog.title}}</h4>
<!--              <h4>{{blog.context}}</h4>-->
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
  //props:['name'],
  data(){
    return{
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
    }
  },
  mounted() {
    this.$axios.post('/api/blog/getBlogsByUserName',{name:this.$store.getters.getUserName,password:null}).then(res=>{
      this.blogs=res.data.data
      console.log(this.blogs)
    })
  }
}
</script>

<style scoped>

</style>
