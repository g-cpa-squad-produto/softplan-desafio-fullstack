import {combineReducers} from 'redux';
import processList from './process-list';
import processDialog from './process-dialog';
import processVinculo from './process-vinculos'

export default combineReducers({
    processList,
    processDialog,
    processVinculo
});