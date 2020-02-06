import React from "react";
import { Switch, Route } from "react-router-dom";

import { Page404 } from "./views/Page404";

const Routes = () => (
    <Switch>
        <Route component={Page404} />
    </Switch>
);

export default Routes;
