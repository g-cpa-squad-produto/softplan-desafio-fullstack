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

const sendGetRequest = async (url, params) => {
    let options = getJwtHeaders();
    options.params = params;
    return axios.get(url, options);
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

    localStorage.setItem('JWT', data.token)
    localStorage.setItem('username', creds.email)
    localStorage.setItem('roles', data.roles)

    commit(types.LOGIN_SUCCESS, {
        token: data.token,
        username: creds.email,
        roles: data.roles
    })

    return status;
}

const carregarToken = ({commit}) => {

    if(localStorage.getItem('JWT')) {

        const data = {
            token: localStorage.getItem('JWT'),
            username: localStorage.getItem('username'),
            roles: localStorage.getItem('roles').split(',')
        }

        commit(types.LOGIN_SUCCESS, data)
    }
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

const buscarUsuarios = async ({commit}, params) => {
    const {data} = await sendGetRequest('/api/usuarios', params)
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

const adicionarUsuarioParecer = async ({commit}, dataSend) => {
    const {data} = await sendPostRequest('/api/processos/' + dataSend.processo.id +'/pareceres/adicionarUsuario', dataSend.usuario)
    return data;
}

const buscarParecerProcesso = async ({commit}, processoId) => {
    const {data} = await sendGetRequest('/api/processos/' + processoId +'/pareceres/atual')
    commit(types.BUSCAR_PARECER_PROCESSO, data)
}

const salvarParecerProcesso = async ({commit}, parecer) => {
    const {data} = await sendPostRequest('/api/processos/' + parecer.processo.id +'/pareceres/' + parecer.id + '/incluirParecer', parecer)
    commit(types.SALVAR_PARECERE_PROCESSO, data)
}

export default {
    [types.LOGIN]: login,
    [types.LOGOUT]: logout,
    [types.LOAD_TOKEN]: carregarToken,
    [types.BUSCAR_PAPEIS]: buscarPapeis,
    [types.BUSCAR_USUARIOS]: buscarUsuarios,
    [types.SALVAR_USUARIO]: salvarUsuario,
    [types.BUSCAR_USUARIO]: buscarUsuario,
    [types.ATRIBUIR_USUARIO_INICIAL]: atribuirUsuarioInicial,
    [types.BUSCAR_PROCESSOS]: buscarProcessos,
    [types.ATRIBUIR_PROCESSO_INICIAL]: atribuirProcessoInicial,
    [types.SALVAR_PROCESSO]: salvarProcesso,
    [types.BUSCAR_PARECERES_PROCESSO]: buscarPareceresProcesso,
    [types.ADICIONAR_USUARIO_PARECER]: adicionarUsuarioParecer,
    [types.BUSCAR_PARECER_PROCESSO]: buscarParecerProcesso,
    [types.SALVAR_PARECERE_PROCESSO]: salvarParecerProcesso

}

