import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class AdminAddUser extends Component {
  render() {
    return (
      <div class="AdminAddUser">
        AdminAddUser
        <Link to="/">AdminUsers</Link>
      </div>
    );
  }
}
