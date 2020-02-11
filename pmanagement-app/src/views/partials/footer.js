import React from "react";
import PropTypes from "prop-types";
import { Container, Row } from "shards-react";

const FooterLayout = ({ contained, copyright }) => (
    <footer className="main-footer">
        <Container fluid={contained}>
            <Row>
                <span className="copyright ml-auto my-auto mr-2">{copyright}</span>
            </Row>
        </Container>
    </footer>
);

FooterLayout.propTypes = {
    contained: PropTypes.bool,
    copyright: PropTypes.string
};

FooterLayout.defaultProps = {
    contained: false,
    copyright: "Copyright © 2020 pManagement"
};

export default FooterLayout;