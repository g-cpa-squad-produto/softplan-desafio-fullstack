import React, { Component } from "react";
import Cookies from "universal-cookie";
import PublicRoutes from "../domain/public";
import PermissionConfig from "./PermissionConfig";
import Header from "../components/Header";
import "./RouterConfig.css";

const cookies = new Cookies();

export default class RouterConfig extends Component {
  render() {
    const token = cookies.get("Authorization");
    const userLoged = token != null && token !== "";
    const PermissionTag = new PermissionConfig();

    return userLoged ? (
      <div>
        <Header />
        <div className="Body">
          <PermissionTag />
        </div>
      </div>
    ) : (
      <PublicRoutes />
    );
  }
}
