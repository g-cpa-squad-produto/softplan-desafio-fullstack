import {actionTypes} from './action';

const initialState = {
    loggedIn: false,
    name: '',
    username: '',
    roles: []
};

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case actionTypes.LOAD:
            return {
                ...state, loggedIn: action.auth.loggedIn, name: action.auth.name, username: action.auth.username, roles: action.auth.roles
            };
        case actionTypes.LOGOUT:
            return {...state, initialState};
        default:
            return {...state};
    }
}