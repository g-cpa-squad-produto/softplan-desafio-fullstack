import React, {Component} from 'react'
import {hasPermission} from "../Methods";

class Can extends Component {
    render() {
        return (
            <div>
                {hasPermission(this.props.roles) && this.props.children}
            </div>
        )
    }
}

export default Can