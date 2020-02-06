import {
    AUTH_LOGIN,
    AUTH_LOGIN_FAILURE,
    AUTH_LOGIN_SUCCESS,
    AUTH_RESET
} from "./authTypes";

const initialState = {
    error: false,
    isLoading: false,
    user: null // { email: "", token: "", expiresIn: 0, role: "TRIADOR | FINALIZADOR | ADMIN"}
};

export const authReducer = (store = initialState, action) => {
    switch (action.type) {
        case AUTH_RESET:
            return {
                ...initialState
            };

        case AUTH_LOGIN:
            return {
                ...store,
                isLoading: true
            };

        case AUTH_LOGIN_SUCCESS:
            return {
                ...store, isLoading: false, error: false, user: action.payload.user
            };

        case AUTH_LOGIN_FAILURE:
            return {
                ...store, isLoading: false, error: true
            };

        default:
            return store;
    }
};