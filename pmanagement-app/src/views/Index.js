import React from 'react';
import { Route, Router, Redirect } from 'react-router';
import { createBrowserHistory } from 'history';
import Cookies from 'universal-cookie';

import { LoginForm } from './login/Form';
import { IndexHome } from './home/Index';

function Index() {
    return (
        <div className="Index">
            <Router history={createBrowserHistory()}>
                <div>
                    <Route path="/login" component={LoginForm} />
                    <Route path="/" exact={true} component={authenticated()} />
                    {/* <Route path="*" component={NotFoundComponent} /> */}
                </div>
            </Router>
        </div>
    );
}

export default Index;

function authenticated() {
    return class extends React.Component {
        constructor(props) {
            super(props);

            this.state = props.state;
            this.cookies = new Cookies();
        }

        render() {
            const { authenticated } = this.cookies.get('authenticated');
            if (authenticated) {
                return (
                    <div className="App">
                        <React.Fragment>
                            <IndexHome />
                        </React.Fragment>
                    </div>
                );
            }

            return (
                <React.Fragment>
                    <Redirect to="/login" />
                </React.Fragment>
            );
        }
    }
}