<template>
  <div>
    <div class="login-wrap" v-show="showLogin">
      <h3>登录</h3>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button v-on:click="login()">登录</button>
      <span v-on:click="ToRegister()">没有账号？马上注册</span>
    </div>
    <div class="clear"></div>
    <div class="register-wrap" v-show="showRegister">
      <h3>注册</h3>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="请输入用户名" v-model="newUsername">
      <input type="text" placeholder="请输入用户邮箱" v-model="newUserMail">
      <input type="password" placeholder="请输入密码" v-model="newPassword">
      <input type="password" placeholder="请验证密码" v-model="newPasswordD">
      <button v-on:click="register()">注册</button>
      <span v-on:click="ToLogin()">已有账号？马上登录</span>
    </div>
  </div>
</template>

<script>
    export default {
        name: "login",
      methods:{
        ToRegister: function () {
          this.showRegister = true
          this.showLogin = false
          console.log(this.showLogin)
          console.log(this.showRegister)
        },
        ToLogin () {
          this.showRegister = false
          this.showLogin = true
        }
      },
      data () {
        return {
          showLogin: true,
          showRegister: false,
          showTishi: false,
          tishi: '',
          username: '',
          password: '',
          newUsername: '',
          newPassword: '',
          newPasswordD: '',
          newUserMail: '',
          logstate: false,
          tableData: [],
          checkPassword: '',
          checkState: ''
        }
      }
    }
</script>

<style scoped>
  .login-wrap{text-align:center;}
  .register-wrap{text-align:center;}
  input{display:block; width:250px; height:40px; line-height:40px; margin:0 auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
  p{color:red;}
  button{display:block; width:250px; height:40px; line-height: 40px; margin:0 auto; border:none; background-color: #01a0b8; color:#fff; font-size:16px; margin-bottom:5px;}
  span{cursor:pointer;}
  span:hover{color: #01a0b8;}
</style>
