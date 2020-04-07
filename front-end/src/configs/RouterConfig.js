import React, { Component } from "react";
import Cookies from "universal-cookie";
import PublicRoutes from "../public";
import PermissionConfig from "./permissions/PermissionConfig";

const cookies = new Cookies();

export default class RouterConfig extends Component {
  render() {
    const token = cookies.get("token");
    const userLoged = token != null && token !== "";
    const PermissionTag = new PermissionConfig();
    return <div>{userLoged ? <PermissionTag /> : <PublicRoutes />}</div>;
  }
}
