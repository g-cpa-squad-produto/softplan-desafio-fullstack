import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {logoutUser} from '../actions/authentication';
import {withRouter} from 'react-router-dom';
import {Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink} from 'reactstrap';

class Header extends Component {
    constructor(props) {
        super(props);

        this.toggleNavbar = this.toggleNavbar.bind(this);
        this.state = {
            collapsed: true
        };
    }

    toggleNavbar() {
        this.setState({
            collapsed: !this.state.collapsed
        });
    }

    onLogout(e) {
        e.preventDefault();
        this.props.logoutUser(this.props.history);
    }

    render() {
        const {isAuthenticated, user} = this.props.auth;
        return (
            <div>
                <Navbar color="light" light>
                    <NavbarBrand href="/" className="mr-auto">Controle de Processos</NavbarBrand>
                    <NavbarToggler onClick={this.toggleNavbar} className="mr-2"/>
                    <Collapse isOpen={!this.state.collapsed} navbar>
                        {
                            isAuthenticated
                            &&
                            <Nav navbar>
                                <NavItem>
                                    <NavLink href="">{user.sub}</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink href="" onClick={this.onLogout.bind(this)}> Logout </NavLink>
                                </NavItem>
                            </Nav>
                        }

                    </Collapse>
                </Navbar>
            </div>)
    }
}


Header.propTypes = {
    logoutUser: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    auth: state.auth
})

export default connect(mapStateToProps, {logoutUser})(withRouter(Header));

