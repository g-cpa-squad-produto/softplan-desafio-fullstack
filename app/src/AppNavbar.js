import React, { Component } from 'react';
import {Button, Collapse, Container, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from 'reactstrap';
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
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavbarToggler onClick={this.toggle}/>
            <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                    <NavItem>
                        <NavLink tag={Link} to={"/users"}>Usuários</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink tag={Link} to={"/process"}>Processos</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink target="_blank" href="https://www.linkedin.com/in/antonio-rafael-ortega/">LinkedIn</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink target="_blank" href="https://github.com/antrafa/antonio-rafael-ortega">GitHub</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>;
    }
}

export default AppNavbar;
