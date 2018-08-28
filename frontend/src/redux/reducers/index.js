import { combineReducers } from 'redux';
import account from './account';
import session from './session';
import process from './process';

export default combineReducers({
    account,
    session,
    process
});