import {combineReducers} from 'redux';
import user from './user';
import savedUser from './saved-user';

export default combineReducers({
    user: user,
    saveUser: savedUser
});