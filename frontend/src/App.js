import React from 'react';
import './index.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Main from './pages/Main'
import Login from './pages/Login'

function App() {
  return (
    <BrowserRouter>
      <Switch>
          <Route path="/" exact={true} component={Main} />
          <Route path="/api" component={Main} />
          <Route path="/login" component={Login} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
