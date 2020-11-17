<template>
  <div>
    <el-container  direction="vertical">
      <MainHeader/>
      <el-main>
        <div>
          <el-form :model="editForm" :rules="rules" ref="editForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="标题" prop="title">
              <el-input v-model="editForm.title"></el-input>
            </el-form-item>
            <el-form-item label="内容" prop="context">
              <mavon-editor v-model="editForm.context" style="height: 500px"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('editForm')">{{submit}}</el-button>
              <el-button @click="resetForm('editForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import MainHeader from "../components/MainHeader";
export default {
  name: "NewBlog",
  props:['id'],
  components: {MainHeader},
  data() {
    return {
      submit:"",
      editForm: {
        id:'',
        author_name:'',
        title: '',
        context:'',
        datetime:''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
        ],
      },
    };
  },
  mounted() {
    if(this.id!=="new"){
      this.submit="立即更新";
      this.$axios.get('/api/blog/getBlogById', {
        params:{'id':this.id}
      }).then(res=>{
        this.editForm.id=res.data.data.id;
        this.editForm.author_name=res.data.data.author_name;
        this.editForm.title=res.data.data.title;
        this.editForm.datetime=this.getDate(res.data.data.datetime);
        this.editForm.context=res.data.data.context;
      })
    }else{
      this.submit="立即创建";
    }
  },
  methods: {
    getDate:function(timestamp){
      let now = new Date(timestamp),
        y = now.getFullYear(),
        m = now.getMonth() + 1,
        d = now.getDate();
      return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.editForm.author_name=sessionStorage.getItem("userName");
          this.editForm.datetime=new Date();
          if(this.id==="new"){
            //新建博客
            this.editForm.id=sessionStorage.getItem("userName")+new Date().getTime();
            this.$axios.post("/api/blog/addBlog",this.editForm).then(res=>{
              this.$alert('提交成功', '提示', {
                confirmButtonText: '返回首页',
                callback: action => {
                  this.$router.push("/main");
                }
              });
            })
          }else{
            //更新博客
            this.editForm.id=this.id;
            this.$axios.post("/api/blog/updateBlog",this.editForm).then(res=>{
              this.$alert('更新成功', '提示', {
                confirmButtonText: '返回首页',
                callback: action => {
                  this.$router.push("/main");
                }
              });
            })
          }

        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>
