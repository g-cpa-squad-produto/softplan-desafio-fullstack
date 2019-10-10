import {ACCESS_TOKEN} from "./constants";

const request = async (options) => {

    const headers = new Headers({
        'Content-Type': 'application/json',
    })

    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return await fetch(options.url, options)
        .then(response =>
            response.json().then(json => {
                if(!response.ok) {
                    return Promise.reject(json);
                }
                return json;
            })
        );
};

export function login(loginData) {
    return request({
        url: "/api/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginData)
    });
}

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: "/api/users/me",
        method: 'GET'
    });
}

export function getProcessList(userId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: '/api/processes/user/'+userId,
        method: 'GET'
    });
}

export function getFinalizadores(){
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: '/api/users/finalizadores',
        method: 'GET'
    });
}

export function getProcess(idProcess){
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: '/api/processes/'+idProcess,
        method: 'GET'
    });
}

export function deleteProcess(idProcess){
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: '/api/processes/'+idProcess,
        method: 'DELETE'
    });
}
