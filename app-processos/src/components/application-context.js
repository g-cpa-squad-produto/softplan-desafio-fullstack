import config from 'config';
import { authHeader } from '../helpers';

export const ApplicationContext = {
    setUser,
    getUser
};

function setUser(user) {
    if (user) {
        let userData = JSON.stringify(user);
        localStorage.setItem('user', window.btoa(userData));
    } else {
        localStorage.removeItem('user');
    }
}

function getUser() {
    let userData = localStorage.getItem('user');
    if (userData) {
        let user = window.atob(userData);
        return JSON.parse(user);
    }
}