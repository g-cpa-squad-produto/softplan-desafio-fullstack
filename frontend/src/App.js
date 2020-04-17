import React from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom'
import Main from './pages/Main'
import Login from './pages/login/Login'
import { Provider }  from 'react-redux'
import ReduxToastr from 'react-redux-toastr'
import store from "./store";
import './index.css';

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Switch>
            <Route path="/api" component={Main} />
            <Route path="/login" component={Login} />
            <Redirect from="/" exact={true} to="login" />
        </Switch>
      </BrowserRouter>

      <ReduxToastr
        timeOut={4000}
        newestOnTop={false}
        preventDuplicates
        position="top-left"
        getState={(state) => state.toastr}
        transitionIn="fadeIn"
        transitionOut="fadeOut"
        progressBar
        closeOnToastrClick/>
    </Provider>
  );
}

export default App;
