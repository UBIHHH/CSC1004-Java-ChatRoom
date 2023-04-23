<template>
  <div>
    <el-card class="box-card">
      <h2>Login</h2>
      <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-position="left"
          label-width="70px"
          class="login-from"
      >
        <el-form-item label="Name" prop="uname">
          <el-input v-model="ruleForm.uname"></el-input>
        </el-form-item>
        <el-form-item label="Pass" prop="password">
          <el-input
              type="password"
              v-model="ruleForm.password"
              autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="btnGroup">
        <el-button
            type="primary"
            @click="submitForm('ruleForm')"
            v-loading="loading"
        >Login</el-button
        >
        <el-button @click="resetForm('ruleForm')">reset</el-button>
        <router-link to="/register">
          <el-button style="margin-left: 10px">Register</el-button>
        </router-link>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        uname: "",
        password: "",
      },
      rules: {
        uname: [
          { required: true, message: "Username can't be empty", trigger: "blur" },
        ],
        password: [
          { required: true, message: "Password can't be empty", trigger: "blur" },
        ],
      },
      loading: false, // 是否显示加载动画
    };
  },
  methods: {
    submitForm(formName) {
      // 验证表单中的账号密码是否有效
      this.$refs[formName].validate((valid) => {
        this.loading = true;
        if (valid) {
          let _this = this;
          // 使用 axios 将登录信息发送到后端
          this.axios({
            url: "/api/user/login",
            method: "post",
            headers: {
              "Content-Type": "application/json",
            },
            params: {
              uname: _this.ruleForm.uname,
              password: _this.ruleForm.password,
            },
          }).then((res) => {
            if (res.data.code === "0") {  // 当响应的编码为 0 时，说明登录成功
              sessionStorage.setItem("userInfo", JSON.stringify(res.data.data));
              // 跳转
              this.$router.push('/chat');
              // 显示后端响应的成功信息
              this.$message({
                message: res.data.msg,
                type: "success",
              });
            } else {
              // 显示后端响应的失败信息
              this.$message({
                message: res.data.msg,
                type: "warning",
              });
            }
            _this.loading = false;
            console.log(res);
          });
        } else {
          console.log("error submit!!");
          this.loading = false;
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
/* 设置登录面板居中，宽度为400px */
.box-card {
  margin: auto auto;
  width: 400px;
}
/* 设置登录面板中的表单居中 */
.login-from {
  margin: auto auto;
}
</style>