import Vue from 'vue'
import Router from 'vue-router'

import store from '@/commons/store'
import {tiposUsuario} from '@/commons/constants'

import Login from '@/views/pages/login/Login'
import ProcessoBusca from '@/views/pages/processo/busca/ProcessoBusca'
import ProcessoBuscaPendentes from '@/views/pages/processo/busca/ProcessoBuscaPendentes'
import ProcessoDetalhe from '@/views/pages/processo/detalhe/ProcessoDetalhe'
import ProcessoNovo from '@/views/pages/processo/detalhe/ProcessoNovo'
import UsuarioBusca from '@/views/pages/usuario/busca/UsuarioBusca'
import UsuarioDetalhe from '@/views/pages/usuario/detalhe/UsuarioDetalhe'
import UsuarioNovo from '@/views/pages/usuario/detalhe/UsuarioNovo'
import AcessoNaoAutorizado from '@/views/pages/acesso/AcessoNaoAutorizado'

Vue.use(Router)

const router = new Router({
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
            component: ProcessoBusca,
            meta: {
                autorizacoes: [tiposUsuario.TRIADOR]
            }
        },
        {
            path: '/processos/pendentes',
            name: 'processosPendentes',
            component: ProcessoBuscaPendentes,
            meta: {
                autorizacoes: [tiposUsuario.FINALIZADOR]
            }
        },
        {
            path: '/processos/:processoId',
            name: 'processoDetalhe',
            component: ProcessoDetalhe,
            meta: {
                autorizacoes: [tiposUsuario.TRIADOR, tiposUsuario.FINALIZADOR]
            }
        },
        {
            path: '/processos/novo',
            name: 'processoNovo',
            component: ProcessoNovo,
            meta: {
                autorizacoes: [tiposUsuario.TRIADOR]
            }
        },
        {
            path: '/usuarios',
            name: 'usuarios',
            component: UsuarioBusca,
            meta: {
                autorizacoes: [tiposUsuario.ADMINISTRADOR]
            }
        },
        {
            path: '/usuarios/:usuarioId',
            name: 'usuarioDetalhe',
            component: UsuarioDetalhe,
            meta: {
                autorizacoes: [tiposUsuario.ADMINISTRADOR]
            }
        },
        {
            path: '/usuarios/novo',
            name: 'usuarioNovo',
            component: UsuarioNovo,
            meta: {
                autorizacoes: [tiposUsuario.ADMINISTRADOR]
            }
        },
        {
            path: '/nao-autorizado',
            name: 'acessoNaoAutorizado',
            component: AcessoNaoAutorizado
        }
    ]
})

router.beforeEach((to, from, next) => {
    const usuario = store.state.usuario
    if (perdeuSessao(usuario) && to.name !== 'login') {
        next({name: 'login'})
    } else if (!possuiAutorizacaoNaRota(usuario, to) && to.name !== 'acessoNaoAutorizado') {
        next({name: 'acessoNaoAutorizado'})
    }
    next()
})

function perdeuSessao(usuario) {
    return usuario === null
}

function possuiAutorizacaoNaRota(usuario, to) {
    if (to.meta.autorizacoes === undefined) {
        return true
    }
    return to.meta.autorizacoes.includes(usuario.tipo)
}

export default router
