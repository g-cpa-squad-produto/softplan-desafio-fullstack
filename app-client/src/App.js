
import React, {Component} from 'react';
import './App.css';
import AppLoginComponent from './component/AppLoginComponent';
import AdmApp from './component/AdmApp';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'



class App extends Component {
  render() {
    return (
      <Router>
      <div className="container">
        <Switch>                        
            <Route path="/" exact component={AppLoginComponent}></Route>
            <Route path="/users" exact component={AdmApp}></Route>
        </Switch> 
      </div>
      </Router>
    );
  }
}

export default App;