import { combineReducers } from 'redux'
import usuarioReducer from "../acesso/usuarioReducer";

const rootReducer = combineReducers ({
    usuario : usuarioReducer
})

export default rootReducer