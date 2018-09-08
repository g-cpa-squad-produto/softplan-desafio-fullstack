import { combineReducers } from 'redux';
import login from './modules/login';
import user from './modules/user/index';

export default combineReducers({
    login,
    user
});
