<template>
  <div>
    <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="name">
        <el-input v-model="registerForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPassword">
        <el-input v-model="registerForm.checkPassword" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register('registerForm')">注册</el-button>
        <el-button type="primary" @click="toLogin">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
name: "register",
  data(){
    let checkPassword=(rule, value, callback) =>{
      if (value === "") {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        name: '',
        password: '',
        checkPassword:''
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
        checkPassword: [
          { validator: checkPassword, trigger: 'blur' },
        ]
      }
    };
  },
  methods:{
    register:function(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/api/register",{
            name:this.registerForm.name,
            password:this.registerForm.password
          }).then(res => {
            if (res.data.code === 200) {
              this.$alert(res.data.message, '提示', {
                confirmButtonText: '去登陆',
                callback: action => {
                  this.$router.push("/");
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
      })
    },
    toLogin:function(){
      this.$router.push('/');
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
