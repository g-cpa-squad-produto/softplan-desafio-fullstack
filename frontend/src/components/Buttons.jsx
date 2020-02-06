import React from "react";
import { NavLink } from "react-router-dom";
import PropTypes from "prop-types";

const PinkLink = ({ href, children, isLoading, ...rest }) => {
    return (
        <NavLink
            to={href}
            {...rest}
            className={`pink-button ${isLoading && "is-loading"}`}
        >
            {children}
        </NavLink>
    );
};

PinkLink.propTypes = {
    href: PropTypes.string
};

const PinkButtom = ({ children, type, isLoading, ...rest }) => {
    return (
        <button
            {...rest}
            className={`button pink-button ${isLoading ? "is-loading" : ""}`}
        >
            {children}
        </button>
    );
};

export { PinkLink, PinkButtom };
