import React, {Component} from 'react';
import {destroyAuth, getUserLogged} from '../Methods';

class Navbar extends Component {

    render() {
        return (
            <header className="header-desktop">
                <div className="section__content section__content--p30">
                    <div className="container-fluid">
                        <div className="header-wrap float-right">
                            <div className="header-button">
                                <div className="account-wrap">
                                    <div className="account-item clearfix js-item-menu">
                                        <div className="content">
                                            {getUserLogged() && getUserLogged().name} &nbsp; &nbsp;
                                            <span className="fa fa-power-off" style={{ cursor: 'pointer' }} onClick={destroyAuth} />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header >
        );
    }
}

export default Navbar;