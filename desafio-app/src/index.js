import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware, combineReducers} from 'redux';
import {Provider} from 'react-redux';
import thunk from 'redux-thunk';
import './assets/index.css';
import * as reducers from './store/reducers';
import CssBaseline from '@material-ui/core/CssBaseline';
import App from './app/App';

const store = createStore(combineReducers(reducers), applyMiddleware(thunk));

ReactDOM.render(
    <Provider store={store}>
        <div>
            <CssBaseline/>
            <App/>
        </div>
    </Provider>,
    document.getElementById('root')
);
