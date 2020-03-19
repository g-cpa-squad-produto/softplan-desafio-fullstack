import axios from "axios";
import { getToken, logout } from "./auth";

const api = axios.create({
    baseURL: "http://localhost:8080"
});

api.interceptors.request.use(async config => {
    const token = getToken();
    if (token) {
        config.headers.Authentication = token;
    }
    return config;
});

api.interceptors.response.use(async response => {
    return response;
}, async error => {
        if (error.response !== undefined && error.response.status === 403) {
        logout();
        this.props.history.push("/");
        return null;
    }
    return Promise.reject(error);
});

export default api;