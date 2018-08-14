import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { logoutUser } from '../actions/authentication';
import { withRouter } from 'react-router-dom';

class Navbar extends Component {

    onLogout(e) {
        e.preventDefault();
        this.props.logoutUser(this.props.history);
    }

    render() {
        const { isAuthenticated, user } = this.props.auth;
        const authLinks = (
            <ul className="navbar-nav ml-auto">
                <a className="nav-link" >{user.sub}</a>
                <a href="" className="nav-link" onClick={this.onLogout.bind(this)}>
                    Logout
                </a>
            </ul>
        )
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <Link className="navbar-brand" to="/">Controle de Processos</Link>

                {isAuthenticated &&
                (
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        {authLinks}
                    </div>
                )
                }

            </nav>
        )
    }
}

Navbar.propTypes = {
    logoutUser: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    auth: state.auth
})

export default connect(mapStateToProps, { logoutUser })(withRouter(Navbar));
