import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import multi from 'redux-multi'
import reducers from './reducers';

const store = createStore(reducers, applyMiddleware(thunk, multi));

export default store;

export function getToken(){
    return store.getState().session.token;
}