import {createStore, applyMiddleware} from 'redux';
import Reducers from '../redux';
import thunkMiddleware from 'redux-thunk';

const createStoreWithMiddleware = applyMiddleware(thunkMiddleware)(createStore);
export const Store = createStoreWithMiddleware(Reducers);
