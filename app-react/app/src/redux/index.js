import { combineReducers } from 'redux';
import login from './modules/login';
import user from './modules/user/index';
import process from './modules/process/process';
import parecer from './modules/parecer/parecer';

export default combineReducers({
    login,
    user,
    process,
    parecer
});
