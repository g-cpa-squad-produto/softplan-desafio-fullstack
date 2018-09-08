import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {connect} from 'react-redux';
import * as actions from '../../redux/modules/login';

const styles = {
    root: {
        flexGrow: 1,
    },
    flex: {
        flexGrow: 1,
    }
};

class LoginApp extends React.Component {

    handleLogout() {
        this.props.logout(() => {
            this.props.history.push('/')
        });
    }

    render() {
        const { classes } = this.props;

        return (
            <div className={classes.root}>
                <AppBar position="static">
                    <Toolbar>
                        <Typography variant="title" color="inherit" className={classes.flex}>
                            Challenge
                        </Typography>
                        {this.props.loading ?
                            <span>Saindo...</span> :
                            (<Button onClick={this.handleLogout.bind(this)} variant="contained">
                                Sair
                            </Button>)
                        }
                    </Toolbar>
                </AppBar>
            </div>
        );
    }
}

function mapStateToProps({login}) {
    return {
        loading: login.logout
    };
}

export default connect(mapStateToProps, actions)(withStyles(styles)(LoginApp));