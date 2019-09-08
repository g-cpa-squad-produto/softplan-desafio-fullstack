import axios from 'axios'
import * as types from './mutation-types'
import router from '../router'

const getJwtHeaders = () => {
    return {
        headers:{
            "Authorization" : 'Softplan ' + localStorage.getItem('JWT')
        }
    }
}

const sendGetRequest = async (url) => {
    return axios.get(url, getJwtHeaders());
}

const sendPostRequest = async (url, data) => {
    return axios.post(url, data, getJwtHeaders());
}

const sendPutRequest = async (url, data) => {
    return axios.put(url, data, getJwtHeaders());
}

const login = async ({commit}, creds) => {
    commit(types.LOGIN)

    const {status, data} = await axios.post('/api/login', creds)

    commit(types.LOGIN_SUCCESS, {
        token: data.token,
        username: creds.email
    })

    return status;
}

const logout = ({commit}) => {
    commit(types.LOGOUT)
    localStorage.removeItem('JWT')
    router.push('/login')
}

const buscarPapeis = async ({commit}) => {

    const {data} = await sendGetRequest('/api/papeis')
    commit(types.BUSCAR_PAPEIS, data)
}

const buscarUsuarios = async ({commit}) => {
    const {data} = await sendGetRequest('/api/usuarios')
    commit(types.BUSCAR_USUARIOS, data)
}

const buscarUsuario = async ({commit}, id) => {
    const {data} = await sendGetRequest('/api/usuarios/' + id)
    commit(types.BUSCAR_USUARIO, data)
}

const atribuirUsuarioInicial = async ({commit}) => {
    let usuario = {
        papel: {}
    }
    commit(types.BUSCAR_USUARIO, usuario)
}

const salvarUsuario = async ({commit}, usuario) => {
    if(!usuario.id) {
        const {data} = await sendPostRequest('/api/usuarios', usuario)
        commit(types.SALVAR_USUARIO, data);
    }else {
        const {data} = await sendPutRequest('/api/usuarios/' + usuario.id, usuario)
        commit(types.SALVAR_USUARIO, data);
    }
}

const buscarProcessos = async ({commit}) => {
    const {data} = await sendGetRequest('/api/processos')
    commit(types.BUSCAR_PROCESSOS, data)
}

const salvarProcesso = async ({commit}, processo) => {
    const {data} = await sendPostRequest('/api/processos', processo)
    commit(types.SALVAR_PROCESSO, data)
}

const atribuirProcessoInicial = async ({commit}) => {
      commit(types.SALVAR_PROCESSO, {})
}

const buscarPareceresProcesso = async ({commit}, processoId) => {
    const {data} = await sendGetRequest('/api/processos/' + processoId +'/pareceres')
    commit(types.BUSCAR_PARECERES_PROCESSO, data)
}

export default {
    [types.LOGIN]: login,
    [types.LOGOUT]: logout,
    [types.BUSCAR_PAPEIS]: buscarPapeis,
    [types.BUSCAR_USUARIOS]: buscarUsuarios,
    [types.SALVAR_USUARIO]: salvarUsuario,
    [types.BUSCAR_USUARIO]: buscarUsuario,
    [types.ATRIBUIR_USUARIO_INICIAL]: atribuirUsuarioInicial,
    [types.BUSCAR_PROCESSOS]: buscarProcessos,
    [types.ATRIBUIR_PROCESSO_INICIAL]: atribuirProcessoInicial,
    [types.SALVAR_PROCESSO]: salvarProcesso,
    [types.BUSCAR_PARECERES_PROCESSO]: buscarPareceresProcesso
}

