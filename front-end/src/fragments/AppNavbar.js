import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from 'reactstrap';
import { Link } from 'react-router-dom';

class AppNavbar extends Component {

    constructor(props) {
        super(props);
        this.state = {isOpen: false};
    }

    toggle = () => {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        const { isAuthenticated, currentUser } = this.props;

        return <Navbar color="light" dark expand="md">
            <NavbarBrand href="/" className="text-black-50">
                { !isAuthenticated && <span>Gerenciamento de Processos</span> }
                { currentUser && currentUser.name }
            </NavbarBrand>
            <NavbarToggler onClick={this.toggle}/>
            <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                    { isAuthenticated && currentUser && currentUser.type === 'ADMIN' &&
                        <NavItem>
                            <Link className="nav-link text-black-50" tag={Link} to={"/users"}>Usuários</Link>
                        </NavItem>
                    }
                    { isAuthenticated && currentUser &&
                        <NavItem>
                            <Link className="nav-link text-black-50" tag={Link} to={"/process"}>Processos</Link>
                        </NavItem>
                    }
                    { isAuthenticated &&
                    <NavItem>
                        <NavLink className="text-black-50" href="#" onClick={this.props.handleLogout}>Sair</NavLink>
                    </NavItem>
                    }
                </Nav>
            </Collapse>
        </Navbar>;
    }
}

export default AppNavbar;
