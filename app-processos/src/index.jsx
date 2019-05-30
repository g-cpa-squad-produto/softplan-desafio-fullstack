import React from 'react';
import { render } from 'react-dom';
import { App } from './app';

import { configureBackend } from './helpers';
configureBackend();

render(
    <App />,
    document.getElementById('root')
);