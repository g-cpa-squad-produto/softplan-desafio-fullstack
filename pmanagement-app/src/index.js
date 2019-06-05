import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import App from './App';
import Home from './views/Home';
import NotFoundComponent from './views/NotFoundComponent';

import './index.css';

ReactDOM.render(
        <BrowserRouter>
            <Switch>
            <Route path="/home" exact={true} component={Home} />
            <Route path="/" exact={true} component={App} />
            <Route component={NotFoundComponent}></Route>
            </Switch>
        </BrowserRouter>,
        document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
