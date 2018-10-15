import React, { Component } from 'react'

class Row extends Component {
    render() {
        return (
            <div style={this.props.style} className={"row "  + this.props.class}>
                {this.props.children}
            </div>
        )
    }
}

export default Row