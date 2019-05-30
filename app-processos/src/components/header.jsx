import React, {Component} from 'react';
import { Link } from 'react-router-dom';

import { ApplicationContext } from './application-context';

class Header extends Component {

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div id="header">
                <nav className="navbar navbar-light bg-light">
                    <a className="navbar-brand" href="http://localhost:8080">
                        <strong>Gerenciador de processos</strong>
                    </a>
                    <span className="navbar-text">
                        <Link to="/login">Logout</Link>
                    </span>
                </nav>
            </div>
        );
    }

}

export { Header };