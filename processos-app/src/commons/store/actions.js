import axios from 'axios'
import {actionTypes, mutationTypes} from '@/commons/constants'

export default {

    async [actionTypes.BUSCAR_USUARIOS]({commit}) {
        const {data} = await axios.get('api/usuarios')
        commit(mutationTypes.SET_USUARIOS, data)
    }

}