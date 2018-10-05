import Vue from 'vue'
import Router from 'vue-router'
import Home from 'components/home'
import Login from 'components/Login'
import UserList from 'components/UserList'
import User from 'components/User'
import ProcessList from 'components/ProcessList'
import Process from 'components/Process'
import store from '../store'

Vue.use(Router)

const ifNotAuthenticated = (to, from, next) => {
  if (!store.getters.isAuthenticated) {
    next()
    return
  }
  next('/')
}

const ifAuthenticated = (to, from, next) => {
  if (store.getters.isAuthenticated) {
    next()
    return
  }
  next('/login')
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      beforeEnter: ifNotAuthenticated,
    },
    {
      path: '/userList',
      name: 'UserList',
      component: UserList,
      beforeEnter: ifAuthenticated,
    },
    {
      path: '/user/:id',
      name: 'User',
      component: User,
      beforeEnter: ifAuthenticated,
    },
    {
      path: '/processList',
      name: 'ProcessList',
      component: ProcessList,
      beforeEnter: ifAuthenticated,
    },
    {
      path: '/process/:id',
      name: 'Process',
      component: Process,
      beforeEnter: ifAuthenticated,
    },
  ],
})
