import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Paper, Table, Typography, withStyles} from '@material-ui/core';
import {styles} from './styles';

class TableCustom extends Component {
    render() {
        const {classes} = this.props;

        return (
            <Paper className={classes.root}>
                <Typography variant="headline" gutterBottom className={classes.title}>{this.props.title}</Typography>
                <Table className={classes.table}>
                    {this.props.tableHead}
                    {this.props.tableBody}
                </Table>
            </Paper>
        );
    }
}

TableCustom.contextTypes = {
    title: PropTypes.string,
    tableHead: PropTypes.element,
    tableBody: PropTypes.element
};

export default withStyles(styles)(TableCustom);