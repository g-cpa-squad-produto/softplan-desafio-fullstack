import React, {Component} from 'react';
import {connect} from 'react-redux';
import {BrowserRouter as Router} from 'react-router-dom';
import GeneralRoutes from '../routes/GeneralRoutes';
import SimpleSnackbar from "../components/notification/SimpleSnackBar";

class App extends Component {
    state = {
        openNotification: false,
        textNotification: ''
    };

    handleOpenNotification = (text) => {
        this.setState({
            openNotification: true,
            textNotification: text
        });
    };

    handleCloseNotification = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        this.setState({openNotification: false});
    };

    render() {
        return (
            <div className="App">
                <Router>
                    <div>
                        <SimpleSnackbar handleOpen={this.handleOpenNotification}
                                        handleClose={this.handleCloseNotification}
                                        open={this.state.openNotification} text={this.state.textNotification}/>
                        <GeneralRoutes notify={this.handleOpenNotification} {...this.props}/>
                    </div>
                </Router>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {auth: state.auth};
}

export default connect(mapStateToProps)(App);
