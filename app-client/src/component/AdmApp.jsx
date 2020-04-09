import React, {Component} from 'react';
import ListUsersComponent from './ListUsersComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import UserComponent from './UserComponent';
import AppLoginComponent from './AppLoginComponent';

class UserApp extends Component {

    render() {
        return (
            <Router>
                <div className="container">
                    <h1>Administrador</h1> 
                    <hr/>
                    <Switch>                        
                        <Route path="/" exact component={AppLoginComponent}></Route>
                        <Route path="/users" exact component={ListUsersComponent}></Route>
                        <Route path="/users/:id" component={UserComponent}></Route>
                    </Switch>
                </div>
            </Router>
        )
    }
}


export default UserApp