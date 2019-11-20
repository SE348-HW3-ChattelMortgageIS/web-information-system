import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import home from '../view/home'
import login from '../view/login'
import setting from '../view/setting'
import inStore from '../view/InStore'
import steelPage from '../view/SteelPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      component: home
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/setting',
      component: setting
    },
    {
      path: '/instore',
      component:inStore
    },
    {
      path: '/steel',
      component:steelPage
    }
  ]
})
