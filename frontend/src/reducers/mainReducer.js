import { GET_ACTION, POST_ACTION, PUT_ACTION, DELETE_ACTION } from '../actions/types';

const initialState = {
    role: '',
    get: {},
    post: {},
    put: {},
    delete: {}
}

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_ACTION:
            return {
                ...state,
                get: action.payload
            }
        case POST_ACTION:
            return {
                ...state,
                post: action.payload
            }
        case PUT_ACTION:
            return {
                ...state,
                put: action.payload
            }
        case DELETE_ACTION:
            return {
                ...state,
                delete: action.payload
            }
        default:
            return state;
    }
}