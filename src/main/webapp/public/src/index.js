import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import UsuarioBox from './components/Usuario';
import ProcessoBox from './components/Processo';
import Login from './components/Login';
import Logout from './components/Logout';
import './index.css';
import { BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';

function isLoggedIn() {
    return localStorage.getItem('usuario') === null;
}

ReactDOM.render(
    
    (<Router>
        <App path="/" component={App}>
            <Switch>
                <Route exact path="/" component={Login} />
                <Route path="/logout" component={Logout} />
                <Route path="/usuario" render={() => (isLoggedIn() ? (
                    <Redirect to="/?msg=Você precisa estar logado para acessar o endereço" />
                    ) : ( <UsuarioBox /> ))}/>
                <Route path="/processo" render={() => (isLoggedIn() ? (
                    <Redirect to="/?msg=Você precisa estar logado para acessar o endereço" />
                    ) : ( <ProcessoBox />))}/>
            </Switch>
        </App>
    </Router>), 

    document.getElementById('root')
);

