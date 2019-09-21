import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/pages/login/Login'
import ProcessoBusca from '@/views/pages/processo/busca/ProcessoBusca'
import ProcessoBuscaPendentes from '@/views/pages/processo/busca/ProcessoBuscaPendentes'
import ProcessoDetalhe from '@/views/pages/processo/detalhe/ProcessoDetalhe'
import UsuarioBusca from '@/views/pages/usuario/busca/UsuarioBusca'
import UsuarioDetalhe from '@/views/pages/usuario/detalhe/UsuarioDetalhe'

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
        },
        {
            path: '/processos',
            name: 'processos',
            component: ProcessoBusca
        },
        {
            path: '/processos/pendentes',
            name: 'processosPendentes',
            component: ProcessoBuscaPendentes
        },
        {
            path: '/processos/:processoId',
            name: 'processoDetalhe',
            component: ProcessoDetalhe
        },
        {
            path: '/usuarios',
            name: 'usuarios',
            component: UsuarioBusca
        },
        {
            path: '/usuarios/:usuarioId',
            name: 'usuarioDetalhe',
            component: UsuarioDetalhe
        }
    ]
})
