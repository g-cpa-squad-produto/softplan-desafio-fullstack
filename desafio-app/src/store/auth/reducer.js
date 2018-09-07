import {actionTypes} from './action';

const initialState = {
    loggedIn: false,
    username: '',
    roles: []
};

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case actionTypes.LOAD:
            return {
                ...state, loggedIn: action.auth.loggedIn, username: action.auth.username, roles: action.auth.roles
            };
        case actionTypes.LOGOUT:
            return {...state, initialState};
        default:
            return {...state};
    }
}