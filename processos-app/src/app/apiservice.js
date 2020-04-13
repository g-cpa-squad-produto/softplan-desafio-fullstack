import axios from 'axios'

export const httpClient = axios.create({
    baseURL: 'https://processos--api.herokuapp.com/'
    //baseURL: 'http://localhost:8080'
})

class ApiService {

    constructor(apiurl){git 
        this.apiurl = apiurl;
    }

    post(url, objeto){
        const requestUrl = `${this.apiurl}${url}`
        return httpClient.post(requestUrl, objeto);
    }

    put(url, objeto){
        const requestUrl = `${this.apiurl}${url}`
        return httpClient.put(requestUrl, objeto);
    }

    delete(url){
        const requestUrl = `${this.apiurl}${url}`
        return httpClient.delete(requestUrl)
    }

    get(url){
        const requestUrl = `${this.apiurl}${url}`
        return httpClient.get(requestUrl)
    }
}


export default ApiService;