import React, { Component } from 'react';

class Alert extends Component {
    render() {
        return (
            <div className={"sufee-alert alert with-close alert-dismissible fade show " + this.props.color}>
                {this.props.message}
            </div>
        );
    }
}

export default Alert;