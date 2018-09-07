import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware, combineReducers} from 'redux';
import {Provider} from 'react-redux';
import thunk from 'redux-thunk';
import './assets/index.css';
import registerServiceWorker from './registerServiceWorker';
import * as reducers from './store/reducers';
import CssBaseline from '@material-ui/core/CssBaseline';
import {BrowserRouter as Router} from 'react-router-dom';
import Routes from "./routes";

const store = createStore(combineReducers(reducers), applyMiddleware(thunk));

ReactDOM.render(
    <Provider store={store}>
        <div>
            <CssBaseline/>
            <Router>
                <Routes/>
            </Router>
        </div>
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
