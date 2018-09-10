import {combineReducers} from 'redux';
import parecerList from './parecer-list';
import ParecerDialog from './parecer-dialog';

export default combineReducers({
    parecerList,
    ParecerDialog
});