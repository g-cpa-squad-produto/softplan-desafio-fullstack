import React from "react";
import { Route, Redirect } from "react-router-dom";
import isAuthorized from "../../util/isAuthorized";

export default function PrivateRoute({ component: Component, ...attr }) {
  return (
    <Route
      {...attr}
      render={(props) => {
        if (isAuthorized()) {
          return <Component {...props} />;
        } else {
          return <Redirect to={{ pathname: "/", state: { from: props.location } }} />;
        }
      }}
    />
  );
}
