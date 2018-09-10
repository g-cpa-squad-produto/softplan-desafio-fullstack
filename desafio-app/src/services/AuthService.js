import jwt_decode from 'jwt-decode';
import cookie from 'react-cookie';
import {ACCESS_TOKEN} from "../constants";

export default class AuthService {

    constructor(api) {
        this.api = api;
    }

    login(username, password, onSuccess, onError) {
        return this.api.post('/auth/login', JSON.stringify({username, password}), {headers: {'Content-Type': 'application/json'}})
            .then(res => {
                const token = res.data.accessToken;
                cookie.save(ACCESS_TOKEN, token, {path: '/'});
                res.data = this.getUserDetails();

                if (onSuccess) onSuccess(res);
            }).catch(onError);
    }

    logout(onSuccess) {
        cookie.remove(ACCESS_TOKEN, {path: '/'});
        if (onSuccess) onSuccess();
    }

    getUserDetails() {
        const token = cookie.load(ACCESS_TOKEN); //pegar user details do login

        if (!token) {
            return {loggedIn: false, roles: ['ROLE_ANONYMOUS']};
        }

        const user = JSON.parse(jwt_decode(token).sub);

        return {
            token: token,
            loggedIn: !this.isTokenExpired(token),
            name: user.name,
            username: user.username,
            roles: user.authorities.map(authority => authority.authority)
        };
    }

    getTokenExpirationDate(token) {
        const decoded = jwt_decode(token);

        if (!decoded.exp)
            return null;

        const date = new Date(0);
        date.setUTCSeconds(decoded.exp);

        return date;
    }

    isTokenExpired(token) {
        const date = this.getTokenExpirationDate(token);
        const offsetSeconds = 0;

        if (date === null) return false;

        return !(date.valueOf() > (new Date().valueOf() + (offsetSeconds * 1000)));
    }
}