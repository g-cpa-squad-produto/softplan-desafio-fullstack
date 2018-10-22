import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {auth: true}
    },

    {
      path: '/usuario/form',
      name: 'UsuarioForm',
      component: () => import(/* webpackChunkName: "usuario" */ '@/views/usuario/Form.vue'),
      meta: {auth: 'ADMIN'}
    },
    {
      path: '/usuario/list',
      name: 'UsuarioList',
      component: () => import(/* webpackChunkName: "usuario" */ '@/views/usuario/List.vue'),
      meta: {auth:'ADMIN'}
    },

    {
      path: '/processo/form',
      name: 'ProcessoForm',
      component: () => import(/* webpackChunkName: "processo" */ '@/views/processo/Form.vue'),
      meta: {auth: 'TRIADOR'}
    },
    {
      path: '/processo/list',
      name: 'ProcessoList',
      component: () => import(/* webpackChunkName: "processo" */ '@/views/processo/List.vue'),
      meta: {auth: 'TRIADOR'}
    },
    {
      path: '/processo/pendente/usuario/',
      name: 'ProcessoPendentesParecerUsuarioList',
      component: () => import(/* webpackChunkName: "processo" */ '@/views/processo/PendenteParecerUsuario.vue'),
      meta: {auth: 'FINALIZADOR'}
    },

    {
      path: '/parecer/form',
      name: 'ParecerForm',
      component: () => import(/* webpackChunkName: "parecer" */ '@/views/parecer/Form.vue'),
      meta: {auth: 'FINALIZADOR'}
    },

    {
      path: '/login',
      name: 'LoginForm',
      component: () => import(/* webpackChunkName: "login" */ '@/views/login/Login.vue'),
      meta: {auth: false}
    },
  ]
})
