import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import Can from "./Can";

class Sidebar extends Component {

    render() {
        return (
            <aside className="menu-sidebar d-none d-lg-block">
                <div className="logo">
                    <h3>GERENCIAMENTO DE PROCESSOS</h3>
                </div>
                <div className="menu-sidebar__content js-scrollbar1">
                    <nav className="navbar-sidebar">
                        <ul className="list-unstyled navbar__list">
                            <li><Link to="/processo"><i className="fas fa-file-text"></i>PROCESSOS</Link></li>
                            <Can roles={'ROLE_USER_GRANT_ALL'}>
                                <li><Link to="/usuario"><i className="fas fa-user"></i>USUÁRIOS</Link></li>
                            </Can>
                        </ul>
                    </nav>
                </div>
            </aside>
        );
    }
}

export default Sidebar;