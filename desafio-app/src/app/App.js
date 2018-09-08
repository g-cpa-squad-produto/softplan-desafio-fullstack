import React, {Component} from 'react';
import {connect} from 'react-redux';
import {BrowserRouter as Router} from 'react-router-dom';
import GeneralRoutes from '../routes/GeneralRoutes';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Router>
                    <GeneralRoutes/>
                </Router>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {auth: state.auth};
}

export default connect(mapStateToProps)(App);
