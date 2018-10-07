import Vue from 'vue'
import Router from 'vue-router'
import Home from 'components/Home'
import Profile from 'components/Profile'
import Login from 'components/Login'
import UserList from 'components/UserList'
import User from 'components/User'
import ProcessList from 'components/ProcessList'
import Process from 'components/Process'
import store from '../store'
import {AUTH_REQUEST} from 'actions/auth'

Vue.use(Router)

const ifNotAuthenticated = (to, from, next) => {
  if (localStorage.getItem('userData')) {
    const userData = JSON.parse(localStorage.getItem('userData'))
    const email = userData.email
    const password = userData.password
    store.dispatch(AUTH_REQUEST, { email, password }).then(() => {
      if (!store.getters.isAuthenticated) {
        next()
        return
      }
      next('/home')
    })
  } else {
    next()
  }
}

const isAdmin = (to, from, next) => {
  if (localStorage.getItem('userData')) {
    const userData = JSON.parse(localStorage.getItem('userData'))
    const email = userData.email
    const password = userData.password
    store.dispatch(AUTH_REQUEST, { email, password }).then(() => {
      if (store.getters.isAdmin) {
        next()
        return
      }
      next('/home')
    })
  } else {
    next('/login')
  }
}

const isNotAdmin = (to, from, next) => {
  if (localStorage.getItem('userData')) {
    const userData = JSON.parse(localStorage.getItem('userData'))
    const email = userData.email
    const password = userData.password
    store.dispatch(AUTH_REQUEST, { email, password }).then(() => {
      if (!store.getters.isAdmin) {
        next()
        return
      }
      next('/home')
    })
  } else {
    next('/login')
  }
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
    },
    {
      path: '/profile',
      name: 'Profile',
      component: Profile,
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
      beforeEnter: isAdmin,
    },
    {
      path: '/user/:id',
      name: 'User',
      component: User,
      beforeEnter: isAdmin,
    },
    {
      path: '/processList',
      name: 'ProcessList',
      component: ProcessList,
      beforeEnter: isNotAdmin,
    },
    {
      path: '/process/:id',
      name: 'Process',
      component: Process,
      beforeEnter: isNotAdmin,
    },
  ],
})
