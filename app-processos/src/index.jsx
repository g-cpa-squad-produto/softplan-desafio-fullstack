import React from 'react';
import { render } from 'react-dom';
import { App } from './app';

// setup fake backend
import { configureFakeBackend } from './helpers';
configureFakeBackend();

render(
    <App />,
    document.getElementById('root')
);