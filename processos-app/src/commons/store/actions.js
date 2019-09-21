import axios from 'axios'
import {actionTypes, mutationTypes} from '@/commons/constants'

export default {

    async [actionTypes.BUSCAR_PROCESSOS]() {
        const {data} = await axios.get('api/processos')
        return data
    },

    async [actionTypes.BUSCAR_PROCESSOS_PENDENTES]({state}) {
        const usuarioId = state.usuario.id
        const {data} = await axios.get(`api/processos/pendentes/${usuarioId}`)
        return data
    },

    async [actionTypes.BUSCAR_USUARIOS]({commit}) {
        const {data} = await axios.get('api/usuarios')
        commit(mutationTypes.SET_USUARIOS, data)
    }

}