import React from "react";
import { Container, Row, Col } from "shards-react";
import MainHeader from "./header";
import MainFooter from "./footer";
import MainContent from "./content";

const MainLayout =({showHeader, showFooter, children}) => (
    <Container fluid>
        <Row>
            <Col lg={{ size: 10, offset: 2 }} md={{ size: 9, offset: 3 }} sm="12" tag="main">
                {showHeader && <MainHeader />}
                {children && <MainContent>{children}</MainContent>}
                {showFooter && <MainFooter />}
            </Col>
        </Row>
    </Container>
);

MainLayout.defaultProps = {
    showHeader: true,
    showFooter: true
};

export default MainLayout;