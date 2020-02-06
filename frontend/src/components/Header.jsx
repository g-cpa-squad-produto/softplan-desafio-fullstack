import React, { useState } from "react";
import { connect } from "react-redux";
import { NavLink } from "react-router-dom";

import { logout } from "../store/auth/authActions";

const iconStyle = { marginRight: 10 };

const Header = props => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => setIsMenuOpen(!isMenuOpen);
    const isActive = isMenuOpen && "is-active";

    const { user } = props.auth;

    return (
        <header className="hero">
            <div className="hero-head is-bg-white">
                <div
                    className="navbar has-shadow"
                    role="navigation"
                    aria-label="main navigation"
                >
                    <div className="container">
                        <div className="navbar-brand">
                            <a
                                onClick={toggleMenu}
                                role="button"
                                className={`navbar-burger burger ${isActive}`}
                                aria-label="menu"
                                aria-expanded="false"
                                data-target="navMenu"
                            >
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>

                        <div
                            id="navMenu"
                            className={`navbar-menu ${isActive}`}
                            style={{
                                backgroundColor: "#FFF"
                            }}
                        >
                            <div class="navbar-start">
                                <NavLink className="navbar-item" to="/usuarios">
                                    Usuarios
                                </NavLink>
                                <NavLink
                                    className="navbar-item"
                                    to="/processos"
                                >
                                    Processos
                                </NavLink>
                            </div>

                            <div className="navbar-end">
                                <div class="navbar-item has-dropdown is-hoverable">
                                    <a class="navbar-link">
                                        <i
                                            className="material-icons"
                                            style={iconStyle}
                                        >
                                            person
                                        </i>
                                        {user.email}
                                    </a>

                                    <div class="navbar-dropdown">
                                        <a
                                            class="navbar-item"
                                            onClick={() => props.logout()}
                                        >
                                            <i
                                                className="material-icons"
                                                style={iconStyle}
                                            >
                                                power_settings_new
                                            </i>
                                            Sair
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

const mapStateToProps = state => {
    return { auth: state.auth };
};

export default connect(mapStateToProps, { logout })(Header);
