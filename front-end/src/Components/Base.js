import React, { Component } from 'react';
import Navbar from './Navbar';
import Sidebar from './Sidebar';

class Base extends Component {

    render() {
        return (
            <div className="page-wrapper">
                <Sidebar />
                <div className="page-container">
                    <Navbar props={this.props} />
                    <div className="main-content">
                        <div className="section__content section__content--p30">
                            {this.props.children}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Base;