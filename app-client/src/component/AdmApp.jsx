import React, {Component} from 'react';
import ListUsersComponent from './ListUsersComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserComponent from './UserComponent';
import AppLoginComponent from './AppLoginComponent';

class UserApp extends Component {

    constructor(props) {
        super(props)
        this.exitClicked = this.exitClicked.bind(this)
    }

    exitClicked(){
        this.props.history.push('/')
    }

    render() {
        return (
            <Router>
                <div className="container">
                    <h1>Administrador</h1> 
                    <hr/>
                    <Switch>                        
                        <Route path="/" exact component={AppLoginComponent}/>
                        <Route path="/users" exact component={ListUsersComponent}/>
                        <Route path="/users/:id" component={UserComponent}/>
                    </Switch>
                    <button className="btn btn-default" onClick={this.exitClicked}>Sair</button>
                </div>
            </Router>
        )
    }
}

export default UserApp