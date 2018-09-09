import React, {Component} from 'react';
import {Typography, withStyles} from '@material-ui/core';
import {styles} from "./styles";

class Dashboard extends Component {
    render() {
        const { classes } = this.props;

        return (
            <main className={classes.content}>
                <div className={classes.appBarSpacer} />
                <Typography variant="display1" gutterBottom>
                    Olá, seja bem-vindo!
                </Typography>
            </main>
        );
    }
}

export default withStyles(styles)(Dashboard);