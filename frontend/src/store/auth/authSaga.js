import { put, takeLatest} from "redux-saga/effects";
import { toast } from "react-toastify";

import auth from "../../services/auth";
import { api, errorHandler } from "../../services/api";
import {
    AUTH_LOGIN,
    AUTH_LOGOUT,
} from "./authTypes";

import {
    loginFailure,
    loginSuccess,
    reset,
} from "./authActions";

function* handleLogin(action) {
    try {
        const { email, password } = action.payload;
        
        const response = yield api.post("/api/v1/auth/login", {
            email, senha: password
        });
        auth.saveUser(response.data);

        yield put(loginSuccess(response.data));
    } catch (error) {
        errorHandler(error, { 401: "Acesso não autorizado." });
        yield put(loginFailure(error));
    }
}

function* handleLogout() {
    try {
        
        yield put(reset());
    } catch (error) {
        toast.error("Ops, ocorreu um erro inesperado");
    }
}

export const authSaga = [
    takeLatest(AUTH_LOGIN, handleLogin),
    takeLatest(AUTH_LOGOUT, handleLogout),
];
