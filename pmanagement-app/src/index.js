import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import * as serviceWorker from './serviceWorker';
import Index from './views/App';

export default axios.create({
    baseURL: 'http://localhost:8080/'
});

ReactDOM.render(
    <Index />,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
