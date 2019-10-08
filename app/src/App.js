import React, {Component} from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserList from './UserList';
import UserEdit from "./UserEdit";
import ProcessList from "./ProcessList";
import ProcessEdit from "./ProcessEdit";
import ProcessUsers from "./ProcessUsers";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/users' exact={true} component={UserList}/>
                    <Route path='/users/:id' exact={true} component={UserEdit}/>
                    <Route path='/process' exact={true} component={ProcessList}/>
                    <Route path='/process/:id' exact={true} component={ProcessEdit}/>
                    <Route path='/process/users/:id' exact={true} component={ProcessUsers}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
