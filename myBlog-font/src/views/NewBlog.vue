<template>
  <div>
    <el-container>
      <el-header>
        <MainHeader/>
      </el-header>
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
              <el-button type="primary" @click="submitForm('editForm')">立即创建</el-button>
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
  components: {MainHeader},
  data() {
    return {
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
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.editForm.author_name=this.$store.getters.getUserName;
          this.editForm.datetime=new Date();
          this.editForm.id=this.$store.getters.getUserName+new Date().getTime();
          this.$axios.post("/api/blog/addBlog",this.editForm).then(res=>{
            this.$alert('提交成功', '提示', {
              confirmButtonText: '返回首页',
              callback: action => {
                this.$router.push("/main");
              }
            });
          })
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
