import Config from '../commons/config';

const UsuariosService = {
    login,
    logout,
    getLoggedUser,
    save,
    remove,
    findAll,
    findAllByPerfil
};

const urlBase = `${Config.URL_API}/usuarios`;

function login(username, password) {
    const url = `${urlBase}/login`;
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: username, senha: password })
    };
    return fetch(url, requestOptions)
        .then(handleLoginResponse)
        .then(storageUserResponse);
}

function logout() {
    localStorage.removeItem('user');
}

function getLoggedUser() {
    let userData = localStorage.getItem('user');
    if (userData) {
        let user = window.atob(userData);
        return JSON.parse(user);
    }
    return null;
}

function save(id, usuario) {
    const url = id ? `${urlBase}/${id}` : urlBase;
    const requestOptions = {
        method: id ? 'PUT' : 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    };
    return fetch(url, requestOptions);
}

function remove(id) {
    const requestOptions = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
    };
    return fetch(`${urlBase}/${id}`, requestOptions);
}

function findAll() {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    return fetch(urlBase, requestOptions);
}

function findAllByPerfil(perfil) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    return fetch(`${urlBase}/consulta?perfil=${perfil}`, requestOptions);
}

function handleLoginResponse(response) {
    if (response.status === 401) {
        logout();
        return Promise.reject('UNAUTHORIZED');
    }
    return response;
}

function storageUserResponse(response) {
    return response.json()
        .then(user => {
            let userString = JSON.stringify(user);
            localStorage.setItem('user', window.btoa(userString));
            return user;
        })
}

export default UsuariosService;