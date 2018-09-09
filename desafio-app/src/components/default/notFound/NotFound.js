import React, {Component} from 'react';
import {Typography} from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import {styles} from './styles';

class NotFound extends Component {
    render() {
        const {classes} = this.props;

        return (
            <main className={classes.layout}>
                <div className={classes.heroContent}>
                    <Typography variant="display3" align="center" color="textPrimary" gutterBottom>
                        Ops!
                    </Typography>
                    <Typography variant="title" align="center" color="textSecondary" component="p">
                        Erro 404: A página que você está tentando acessar não foi encontrada.
                    </Typography>
                </div>
            </main>
        );
    }
}

export default withStyles(styles)(NotFound);