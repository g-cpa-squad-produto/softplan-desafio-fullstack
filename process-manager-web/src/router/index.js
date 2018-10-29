import Vue from 'vue'
import Router from 'vue-router'
import Workspace from '@/components/Workspace'
import Login from '@/components/Login'

Vue.use(Router)

export const routes = [
  {
    path: '/',
    name: 'Default',
    redirect: {
      name: 'Login'
    }
  },
  {
    path: '/workspace',
    name: 'Workspace',
    component: Workspace
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]
