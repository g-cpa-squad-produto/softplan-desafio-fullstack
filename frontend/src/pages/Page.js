import React, { Component } from 'react';
import { signout } from '../redux/actions';

class Page extends Component {
    isLoggedIn() {
        return this.props.session.token !== null;
    }

    redirect(location) {
        this.props.history.push(location);
    }

    reload() {
        window.location.reload()
    }

    checkSession(types) {
        let isAllowedType;
        if (types !== true) {
            if (!Array.isArray(types)) types = [types];
            isAllowedType = types.includes(this.props.session.account.type);
        } else isAllowedType = true;

        if (!this.isLoggedIn() || !isAllowedType) {
            this.redirect('/login');
        }
    }

    redirectIfLoggedIn() {
        if (this.isLoggedIn()) {
            this.redirect('/home');
        }
    }

    logout(){
        return this.props.dispatch(signout())
            .then(() => {
                this.redirect('/login');
            });
    }
}

export default Page;