import React, { Component } from 'react'

class Container extends Component {
    render() {
        return (
            <div className="container-fluid">
                {this.props.children}
            </div>
        )
    }
}

export default Container