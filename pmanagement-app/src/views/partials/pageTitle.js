import React from "react";
import PropTypes from "prop-types";
import { Col } from "shards-react";

const PageTitle = ({ title, subtitle, className, ...attrs }) => (
    <Col xs="12" sm="4" className="text-center text-md-left {className}" {...attrs}>
        <span className="text-uppercase page-subtitle">{subtitle}</span>
        <h3 className="page-title">{title}</h3>
    </Col>
);

PageTitle.propTypes = {
    title: PropTypes.string,
    subtitle: PropTypes.string
};

export default PageTitle;