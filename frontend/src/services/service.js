import Axios from 'axios';

export const login = (username, password) => {
    let params = new URLSearchParams()
    params.append('username', username)
    params.append('password', password)
    return Axios.post('/authenticate', params)
}

export const getAllUsers = () => {
    return Axios.get('/api/user/')
}

export const getFinUsers = () => {
    return Axios.get('/api/finalizadores/')
}

export const getAnUserById = (id) => {
    return Axios.get('/api/user/' + id)
}

export const saveUser = (userData) => {
    return Axios.post('/api/user/', userData)
}

export const deleteUser = (id) => {
    return Axios.delete('/api/user/' + id)
}

export const getAllProcesses = () => {
    return Axios.get('/api/process/')
}

export const getProcessesByUser = () => {
    return Axios.get('/api/process-assign/')
}

export const getProcessById = (id) => {
    return Axios.get('/api/process/' + id)
}

export const saveProcess = (processData) => {
    return Axios.post('/api/process/', processData)
}

export const deleteProcess = (id) => {
    return Axios.delete('/api/process/' + id)
}

export const acceptProcess = (id) => {
    return Axios.put('/api/process/' + id + '/accept')
}

export const denyProcess = (id) => {
    return Axios.put('/api/process/' + id + '/deny')
}