import axios from 'axios';

const USERNAME = 'username'
const USER_API_URL = 'http://localhost:8080'
const USER_ID_API_URL = `${USER_API_URL}/users/${USERNAME}`
// const USER_ID_API_URL = `${USER_API_URL}`

class UserDataService {

    retrieveAllUsers(name) {
        //console.log('executed retrieveAllUsers')
        return axios.get(`${USER_ID_API_URL}/users`)
    }

    deleteUser(name, id) {
        // console.log('executed deleteUser')
        return axios.delete(`${USER_ID_API_URL}/users/${id}`);
    }

    retrieveUser(name, id){
        //console.log('executed retrieveUser')
        return axios.get(`${USER_ID_API_URL}/users/${id}`)
    }

    updateUser(name, id, user) {
        //console.log('executed updateUser')
        return axios.put(`${USER_ID_API_URL}/users/${id}`, user)
    }

    createUser(name, user){
        //console.log('executed createUser')
        return axios.post(`${USER_ID_API_URL}/users/`, user)
    }
}

export default new UserDataService()