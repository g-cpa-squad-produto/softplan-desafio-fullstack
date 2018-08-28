import React from 'react';
import { connect } from 'react-redux';
import Page from './Page';
import FloatingAlert from '../components/floating-alert';
import { loadSession } from '../redux/actions';

class Master extends Page {
    componentDidMount() {
        this.props.dispatch(loadSession());
    }

    componentDidUpdate() {
    }

    render() {
        const isLoggedIn = this.isLoggedIn();
        
        return (
            <div className="Master">
                { this.props.children }
                <FloatingAlert />
            </div>
        );
    }
}

const mapStateToProps = ({ session }) => {
    return { session };
};

export default connect(mapStateToProps)(Master);