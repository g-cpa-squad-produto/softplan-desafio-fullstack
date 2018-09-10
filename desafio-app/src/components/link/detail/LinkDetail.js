import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {IconButton, Tooltip} from '@material-ui/core';
import {Link} from "react-router-dom";

class LinkDetail extends Component {
    render() {
        return (
            <Link to={this.props.to}>
                <Tooltip title="Detalhar">
                    <IconButton aria-label="Detalhar">
                        {this.props.icon}
                    </IconButton>
                </Tooltip>
            </Link>
        );
    }
}

LinkDetail.contextTypes = {
    to: PropTypes.string,
    icon: PropTypes.element
};

export default LinkDetail;