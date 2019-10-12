import {API_BASE_URL,ACCESS_TOKEN} from "./constants";

const request = async (options) => {

    const headers = new Headers({
        'Content-Type': 'application/json',
    })

    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return await fetch(API_BASE_URL + options.url, options)
        .then(response => {
                if(!response) return;
                return response.json().then(json => {
                    if (!response.ok) {
                        return Promise.reject(json);
                    }
                    return json;
                })
            }
        );
};

export function login(loginData) {
    return request({
        url: "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginData)
    });
}

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request({
        url: "/users/me",
        method: 'GET'
    });
}

export function manipulateData(data){
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("Token não encontrado.");
    }
    return request(data);
}
