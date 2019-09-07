import axios from 'axios'
import * as types from './mutation-types'
import router from '../router'

const login = async ({commit}, creds) => {
    commit(types.LOGIN)

    await axios.post('/api/login', JSON.stringify(creds), {
        headers: {
            'Authorization': 'MySecreteApp'
        }
    })

    // axios.defaults.headers.common['Authorization'] = 'Softplan ' + data.token;

    commit(types.LOGIN_SUCCESS, {
        token: response.data.token,
        username: creds.email
    })

    return status;
}

const logout = ({commit}) => {
    commit(types.LOGOUT)
    localStorage.removeItem('JWT')
    router.push('/login')
}

const buscarUsuarios = async ({commit}) => {
    const {data} = await axios.get('/api/usuarios')
    commit(types.BUSCAR_USUARIOS, data)
}

export default {
    [types.LOGIN]: login,
    [types.LOGOUT]: logout,
    [types.BUSCAR_USUARIOS]: buscarUsuarios,
}

