import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import thunk from 'redux-thunk';
import './assets/index.css';
import App from './app/App';
import registerServiceWorker from './registerServiceWorker';
import * as reducers from './store/reducers';
import CssBaseline from '@material-ui/core/CssBaseline';

const store = createStore(combineReducers(reducers), applyMiddleware(thunk));

ReactDOM.render(
    <Provider store={store}>
        <CssBaseline/>
        <App />
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
