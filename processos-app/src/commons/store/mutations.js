import {mutationTypes} from '@/commons/constants'

export default {

    [mutationTypes.SET_USUARIO](state, usuario) {
        state.usuario = usuario
    },

    [mutationTypes.SET_USUARIOS](state, usuarios) {
        state.usuarios = usuarios
    }
}