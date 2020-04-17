import Axios from 'axios';

const basePath = 'http://localhost:8080'

const getToken = () => {
    return `Bearer ${JSON.parse(sessionStorage.session_data).token}`;
}

export const login = (username, password) => {
    let params = new URLSearchParams()
    params.append('username', username)
    params.append('password', password)
    return Axios.post(basePath + '/authenticate', params)
}

export const getAllUsers = () => {
    return Axios.get(basePath + '/api/user/', { headers: { Authorization: getToken() } })
}

export const getAnUserById = (id) => {
    return Axios.get(basePath + '/api/user/' + id, { headers: { Authorization: getToken() } })
}

export const saveUser = (userData) => {
    return Axios.post(basePath + '/api/user/', userData, { headers: { Authorization: getToken() } })
}

export const deleteUser = (id) => {
    return Axios.delete(basePath + '/api/user/' + id, { headers: { Authorization: getToken() } })
}