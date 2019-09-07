import Vue from 'vue'
import Router from 'vue-router'
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'bootstrap/dist/css/bootstrap.css';

import store from '../store'
import * as types from '../store/mutation-types'
import HelloWorld from '../components/HelloWorld'
import Login from '../components/login/Login'
import UsuarioList from '../components/usuario/UsuarioList'
import NavBar from '../components/layout/NavBar'

const hasToken = (to, from, next) => {
    const token = localStorage.getItem('JWT')
    const username = localStorage.getItem('username')
    if (token) {
        store.commit(types.LOGIN_SUCCESS, { token, username })
        router.push('/home')
    } else {
        next()
    }
}

const requireAuth = (to, from, next) => {


    if (store.getters.isLoggedIn) {
        next()
    } else {
        router.push('/')
    }
}

Vue.use(Router)
Vue.use(BootstrapVue)
Vue.component('NavBar', NavBar)

const router = new Router({
    routes: [
        {
            path: '/',
            alias: '/login',
            name: 'Login',
            component: Login,
            beforeEnter: hasToken
        },
        {
            path: '/home',
            name: 'Home',
            component: HelloWorld,
            beforeEnter: requireAuth
        },
        {
            path: '/usuarios',
            name: 'UsuarioListage',
            component: UsuarioList,
            // beforeEnter: requireAuth
        }
    ]
})

export default router