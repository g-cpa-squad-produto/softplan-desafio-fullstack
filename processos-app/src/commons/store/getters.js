import {tiposUsuario} from '../constants'

export default {
    ehUsuarioTriador(state) {
        return state.usuario.tipo === tiposUsuario.TRIADOR
    },
    ehUsuarioFinalizador(state) {
        return state.usuario.tipo === tiposUsuario.FINALIZADOR
    }
}