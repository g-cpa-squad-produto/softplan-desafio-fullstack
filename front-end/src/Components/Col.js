import React, { Component } from 'react';

class Col extends Component {
    render() {
        return (
            <div className={`col-md-${this.props.col}`}>
                {this.props.children}
            </div>
        );
    }
}

export default Col;