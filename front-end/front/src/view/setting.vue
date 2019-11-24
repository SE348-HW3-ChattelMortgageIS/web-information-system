<template>
    <div>
        <button @click="ToLogin()">登录账号</button>
        <button @click="changePW()">修改密码</button>
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
        }
    }
}
</script>

<style scoped>
    button{display:block; width:250px; height:40px; line-height: 40px; margin:0 auto; border:none; background-color: #01a0b8; color:#fff; font-size:16px; margin-bottom:5px;}
    span{cursor:pointer;}
    span:hover{color: #01a0b8;}
</style>