<template>
    <el-col :span="12">
      <h5>user name</h5>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>表格</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">钢卷</el-menu-item>
            <el-menu-item index="1-2">入仓单</el-menu-item>
            <el-menu-item index="1-3">出仓单</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>扇形图</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1">钢卷</el-menu-item>
            <el-menu-item index="2-2">入仓单</el-menu-item>
            <el-menu-item index="2-3">出仓单</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="3" >
          <i class="el-icon-document"></i>
          <span slot="title">消息中心</span>
        </el-menu-item>
        <el-menu-item index="4" @click="ToSetting()">
          <i class="el-icon-setting"></i>
          <span slot="title">个人设置</span>
        </el-menu-item>
      </el-menu>
    </el-col>
</template>

<script>
    export default {
        name: "leftNavi",
        methods: {
          handleOpen(key, keyPath) {
            console.log(key, keyPath);
          },
          handleClose(key, keyPath) {
            console.log(key, keyPath);
          },
          ToSetting() {
            this.$router.push({path:'/setting'})
          }
        }
    }
</script>

<style scoped>

</style>
