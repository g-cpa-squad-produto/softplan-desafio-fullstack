import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class AdminEditUser extends Component {
  render() {
    return (
      <div class="AdminEditUser">
        AdminEditUser
        <Link to="/">AdminUsers</Link>
      </div>
    );
  }
}
