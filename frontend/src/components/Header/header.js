import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Header extends Component {
    render() {
        return (
            <div>
                <nav className="navbar navbar-light bg-light">
                    <Link to="/" className="navbar-brand">Processos</Link>
                </nav>
                <br />
            </div>
        );
    }
}

export default Header;