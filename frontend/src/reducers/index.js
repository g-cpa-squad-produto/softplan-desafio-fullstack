import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import authReducer from './authReducer';
import mainReducer from './mainReducer';

export default combineReducers({
    errors: errorReducer,
    auth: authReducer,
    main: mainReducer
});