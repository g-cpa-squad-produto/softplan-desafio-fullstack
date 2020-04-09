import React, {Component} from 'react';
import AppLoginComponent from './AppLoginComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ReviewComponent from './ReviewComponent';
import ListReviewComponent from './ListReviewComponent';


class FinalizadorComponent extends Component {

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
                    <h1>Finalizador</h1>
                    <hr/>
                    <Switch>
                        <Route path="/" exact component={AppLoginComponent}/>
                        <Route path="/review" exact component={ListReviewComponent}/>
                        <Route path="/review/:id" component={ReviewComponent}/>
                    </Switch>
                </div>  
            </Router>
            <button className="btn btn-default" onClick={this.exitClicked}>Sair</button>
            </div>
        ) 
    }
}

export default FinalizadorComponent