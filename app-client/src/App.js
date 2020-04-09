
import React, {Component} from 'react';
import './App.css';
import AppLoginComponent from './component/AppLoginComponent';
import AdmApp from './component/AdmApp';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TriadorComponent from './component/TriadorComponent';
import FinalizadorComponent from './component/FinalizadorComponent';


class App extends Component {

  render() {
    return (
      <Router>
      <div className="container">
        <Switch>                        
            <Route path="/" exact component={AppLoginComponent}/>
            <Route path="/users" exact component={AdmApp}/>
            <Route path="/process" exact component={TriadorComponent}/>
            <Route path="/review" exact component={FinalizadorComponent}/>
        </Switch> 
      </div>
      </Router>
    );
  }
}

export default App;