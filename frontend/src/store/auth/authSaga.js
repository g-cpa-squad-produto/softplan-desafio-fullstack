import { put, takeLatest} from "redux-saga/effects";
import { toast } from "react-toastify";
import axios from "axios";

import { api, errorHandler } from "../../api";
import {
    AUTH_LOGIN,
    AUTH_LOGOUT,
} from "./authTypes";

import {
    loginFailure,
    reset,
} from "./authActions";

function* handleLogin(action) {
    try {
        const { email, password } = action.payload;

    } catch (error) {
        toast.error("Dados incorretos");
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
