<template>
  <div>
    <el-container>
      <el-header>
        <MainHeader/>
      </el-header>
      <el-main>
        <div>
          <h2>{{blog.title}}</h2>
          <div>
            <span>{{ blog.author_name }}</span>
            <el-divider direction="vertical"></el-divider>
            <span>{{this.getDate(blog.date)}}</span>
            <el-divider direction="vertical"></el-divider>
            <span @click="this.deleteBlogById" style="cursor: pointer; color: red">删除</span>
          </div>
          <div class="markdown-body" style="text-align: left" v-html="blog.context"></div>
        </div>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import MainHeader from "../components/MainHeader";
import 'github-markdown-css/github-markdown.css';
export default {
  name: "ShowBlog",
  props:['id'],
  components: {MainHeader},
  data(){
    return{
      blog:{
        id:'',
        author_name: '',
        title:'',
        context:'',
        date:'',
      }
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
    deleteBlogById:function(){
      this.$confirm('是否删除该博客？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('/api/blog/deleteBlogById',{
          params:{'id':this.id}
        }).then(res=>{
          this.$router.push("/main");
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    }
  },
  mounted() {
    this.$axios.get('/api/blog/getBlogById', {
      params:{'id':this.id}
    }).then(res=>{
      this.blog.id=res.data.data.id;
      this.blog.author_name=res.data.data.author_name;
      this.blog.title=res.data.data.title;
      this.blog.date=this.getDate(res.data.data.datetime);
      let MarkdownIt=require('markdown-it');
      let md=new MarkdownIt();
      this.blog.context=md.render(res.data.data.context);
    })
  }
}
</script>

<style scoped>

</style>
