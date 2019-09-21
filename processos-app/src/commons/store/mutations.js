import {mutationTypes} from '@/commons/constants'

export default {

    [mutationTypes.SET_ALERT](state, alert) {
        state.alert = alert
    },

    [mutationTypes.SET_USUARIO](state, usuario) {
        state.usuario = usuario
    },

    [mutationTypes.SET_USUARIOS](state, usuarios) {
        state.usuarios = usuarios
    }
}