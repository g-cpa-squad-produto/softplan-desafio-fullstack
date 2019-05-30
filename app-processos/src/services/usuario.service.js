import config from 'config';
import { authHeader } from '../helpers';
import { ApplicationContext } from '../components/application-context';

export const UsuarioService = {
    login,
    logout,
    getAll
};

function login(username, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ loginAcesso: username, senha: password })
    };

    return fetch(`${config.apiUrl}/usuarios/login`, requestOptions)
        .then(handleResponse)
        .then(user => {
            if (user) {
                ApplicationContext.setUser(user);
            }
            return user;
        });
}

function logout() {
    ApplicationContext.setUser(null);
}

function getAll() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/usuarios`, requestOptions);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                logout();
                location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}