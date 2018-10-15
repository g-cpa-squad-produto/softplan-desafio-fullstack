import swal from 'sweetalert';

export function messageAlert(message, type = '', url) {
    if (url) {
        swal("", message, type).then(() => window.location.href = url);
    } else {
        swal("", message, type);
    }
}

export function isAuth() {
    return !!localStorage.getItem('auth');
}

export function setAuth(user) {
    localStorage.setItem('auth', user.token);
    localStorage.setItem('userLogged', JSON.stringify(user));
}

export function getAuth() {
    return localStorage.getItem('auth');
}

export function getUserLogged() {
    return JSON.parse(localStorage.getItem('userLogged'));
}

export function hasPermission(roles) {
    let hasPermission = false;

    if (getUserLogged()) {
        getUserLogged().roles.forEach((role) => {
            if (roles.includes(role)) {
                hasPermission = true;
            }
        });
    }

    return hasPermission;
}

export function destroyAuth() {
    localStorage.clear();
}

export function getUrl() {
    return "http://localhost:8080/";
}