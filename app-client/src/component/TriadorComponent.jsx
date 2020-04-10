import React, {Component} from 'react';
import ListProcessComponent from './ListProcessComponent';
import AppLoginComponent from './AppLoginComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProcessComponent from './ProcessComponent';
import ReviewComponent from './ReviewComponent';


class TriadorComponent extends Component {

    constructor(props) {
        super(props)
        this.exitClicked = this.exitClicked.bind(this)
    }

    exitClicked(){
        this.props.history.push('/')
    }
    
    render() {
        return (
            <div className="container">
            <Router>
                <div className="container">
                    <h1>Triador</h1>
                    <hr/>
                    <Switch>
                        <Route path="/" exact component={AppLoginComponent}/>
                        <Route path="/process" exact component={ListProcessComponent}/>
                        <Route path="/process/:id" component={ProcessComponent}/>
                        <Route path="/process/:id/review/:id" component={ReviewComponent}/>
                    </Switch>
                </div>  
            </Router>
            {/* <div className="div-exit"> */}
                <button className="btn btn-default btn-exit" onClick={this.exitClicked}>Sair</button>
            {/* </div> */}
            </div>
        ) 
    }
}

export default TriadorComponent