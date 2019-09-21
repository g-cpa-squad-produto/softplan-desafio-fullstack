import axios from 'axios'
import {actionTypes, mutationTypes} from '@/commons/constants'

export default {

    async [actionTypes.BUSCAR_PARECERES_PROCESSO](context, processoId) {
        const {data} = await axios.get(`api/processos/${processoId}/pareceres`)
        return data
    },

    async [actionTypes.BUSCAR_PROCESSO](context, processoId) {
        const {data} = await axios.get(`api/processos/${processoId}`)
        return data
    },

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
    },

    async [actionTypes.INSERIR_PROCESSO](context, processo) {
        const {data} = await axios.post('api/processos', processo)
        return data
    },

    async [actionTypes.REALIZAR_PARECER](context, {processoId, usuarioId, textoParecer}) {
        const {data} = await axios.put(`api/processos/${processoId}/parecer/${usuarioId}`, {textoParecer})
        return data
    },

    async [actionTypes.SOLICITAR_PARECER](context, {processoId, usuariosParecer}) {
        const {data} = await axios.post(`api/processos/${processoId}/pareceres`, usuariosParecer)
        return data
    }

}