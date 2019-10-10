import React from 'react';
import {
    Route,
    Redirect
} from "react-router-dom";


const PrivateRoute = ({ component: Component, authenticated, currentUser, ...rest }) => {
    return (
        <Route
            {...rest}
            render={props =>
                authenticated ? (
                    <Component {...rest} {...props} currentUser={currentUser} />
                ) : (
                    <Redirect
                        to={{
                            pathname: '/login',
                            state: { from: props.location }
                        }}
                    />
                )
            }
        />
    );
}

export default PrivateRoute
