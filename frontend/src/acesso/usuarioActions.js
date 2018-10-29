import axios from 'axios'

const URL = 'http://localhost:8081/desafiosoftplan'
const SERVICE = '/usuarios'

export const changeNome = event => ({
    type: 'NOME_CHANGED',
    payload: event.target.value
})

export const search = () =>{
    const request = axios.get(`${URL}${SERVICE}`) 
    return {
        type: 'USUARIO_SEARCHED',
        payload: request
    }
}

export const add = (nome, login, senha, tipoUsuario) => {
    const request = axios.post(`${URL}${SERVICE}`,{nome, login, senha, tipoUsuario})
    return {
        type: 'USUARIO_ADDED',
        payload: request
    }
}