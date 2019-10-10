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

        return <Navbar color="dark" dark expand="md">
            <NavbarBrand href="/">Gerenciamento de Processos</NavbarBrand>
            <NavbarToggler onClick={this.toggle}/>
            <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                    { isAuthenticated && currentUser && currentUser.type == 'ADMIN' &&
                        <NavItem>
                            <Link className="nav-link text-white" tag={Link} to={"/users"}>Usuários</Link>
                        </NavItem>
                    }
                    { isAuthenticated && currentUser &&
                        <NavItem>
                            <Link className="nav-link text-white" tag={Link} to={"/process"}>Processos</Link>
                        </NavItem>
                    }
                    <NavItem>
                        <NavLink className="text-white" target="_blank" href="https://www.linkedin.com/in/antonio-rafael-ortega/">LinkedIn</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink className="text-white" target="_blank" href="https://github.com/antrafa/antonio-rafael-ortega">GitHub</NavLink>
                    </NavItem>
                    { isAuthenticated &&
                    <NavItem>
                        <NavLink className="text-white" href="#" onClick={this.props.handleLogout}>Sair</NavLink>
                    </NavItem>
                    }
                </Nav>
            </Collapse>
        </Navbar>;
    }
}

export default AppNavbar;
