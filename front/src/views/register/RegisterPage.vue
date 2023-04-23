<template>
  <div>
    <el-card class="box-card">
      <h2>Register</h2>
      <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-position="left"
          label-width="80px"
          class="demo-ruleForm"
      >
        <el-form-item label="Username" prop="uname">
          <el-input v-model="ruleForm.uname"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="pass">
          <el-input
              type="password"
              v-model="ruleForm.pass"
              autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="Confirm Password" prop="password">
          <el-input
              type="password"
              v-model="ruleForm.password"
              autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="email" prop="email">
              <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="address" prop="address">
              <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
      </el-form>
      <div class="btnGroup">
        <el-button type="primary" @click="submitForm('ruleForm')"  v-loading="loading"
        >Submit</el-button
        >
        <el-button @click="resetForm('ruleForm')">reset</el-button>
        <el-button @click="goBack">back</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("please input password"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("please input password again"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("two passwords are inconsistent!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        uname: "",
        pass: "",
        password: "",
      },
      rules: {
        uname: [
          { required: true, message: "username can't be empty", trigger: "blur" },
        ],
        pass: [{ required: true, validator: validatePass, trigger: "blur" }],
        password: [
          { required: true, validator: validatePass2, trigger: "blur" },
        ],
      },
      loading: false
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        this.loading = true;
        if (valid) {
          let _this = this;
          this.axios({
            url: "/api/user/register",
            method: "post",
            headers: {
              "Content-Type": "application/json",
            },
            data: { // 请求参数，为 data，与登录的 params 不太一样
              uname: _this.ruleForm.uname,
              password: _this.ruleForm.password,
              email: _this.ruleForm.email,
              address: _this.ruleForm.address,
            },
          }).then((res) => {
            if (res.data.code === '0') {
              // 显示后端响应的成功信息
              this.$message({
                message: res.data.msg,
                type: "success",
              });
            }else{
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
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.box-card {
  margin: auto auto;
  width: 400px;
}

.login-from {
  margin: auto auto;
}
</style>