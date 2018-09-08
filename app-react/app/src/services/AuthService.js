import Cookies from 'js-cookie';

const AUTH_COOKIE_KEY = 'challenge_auth';
const USER_COOKIE_KEY = 'challenge_user';

export default {
    get: function() {
        return Cookies.get(AUTH_COOKIE_KEY);
    },
    set: function(value) {
        return Cookies.set(AUTH_COOKIE_KEY, value);
    },
    remove: function() {
        Cookies.remove(AUTH_COOKIE_KEY);
        Cookies.remove(USER_COOKIE_KEY);
    },
    setUser: function(user) {
        return Cookies.set(USER_COOKIE_KEY, user);
    },
    getUser: function() {
        return Cookies.get(USER_COOKIE_KEY);
    }
};
