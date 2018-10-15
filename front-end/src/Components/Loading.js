import React, { Component } from 'react';
import Loader from 'react-loader-spinner'

export default class Loading extends Component {
    render() {
        return (
            <Loader
                type="Rings"
                color="#666"
                height="100"
                width="100"
            />
        );
    }
}