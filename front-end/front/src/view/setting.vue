<template>
    <div>
        <button @click="ToLogin()">登录账号</button>
        <button @click="changePW()">修改密码</button>
      <button @click="logout()">退出登录</button>
        <span @click="ret()">返回</span>

    </div>
</template>

<script>
export default{
    name:'setting',
    methods:{
        ToLogin() {
            this.$router.push({path: '/login'})
        },
        changePW() {

        },
        ret() {
            this.$router.push({ path: '/home'})
        },
      logout(){
        // var url='http://101.132.73.215:8080/logout'
        this.$http.get('http://101.132.73.215:8080/logout').then((res) => {
          console.log(res.data)
        }).catch(function (err) {
          alert(err)
        })
        this.$router.push({ path: '/home'})
      }
    }
}
</script>

<style scoped>
    button{display:block; width:250px; height:40px; line-height: 40px; margin:0 auto; border:none; background-color: #01a0b8; color:#fff; font-size:16px; margin-bottom:5px;}
    span{cursor:pointer;}
    span:hover{color: #01a0b8;}
</style>
