<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button type="primary" @click="toRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        name: '',
        password: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    //登录
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/api/login",this.ruleForm).then(res => {
            if(res.data.code===200){
              //校验通过
              sessionStorage.setItem("loginSession",res.data.data.id);
              //this.$store.commit('setUserName',this.ruleForm.name) //设置全局变量
              sessionStorage.setItem('userName',this.ruleForm.name);
              this.$router.push({
                name:'main',
                params:{
                  name:this.ruleForm.name
                }
              });
            }else{
              this.$message({
                showClose: true,
                message: res.data.message,
                type: 'error'
              });
            }
          })

        }
      });
    },
    //重置
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // }
    //注册
    toRegister:function(){
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>
  .demo-ruleForm{
    width: 400px;
    margin: 0 auto;
  }
</style>
