import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/pages/login/Login'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            redirect: 'login'
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        }
    ]
})
