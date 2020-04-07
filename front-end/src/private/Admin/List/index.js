import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class AdminUsers extends Component {
  render() {
    console.log('adminusers')
    return (
      <div class="AdminUsers">
        AdminUsers
        <Link to="/add">AddUser</Link>
        <Link to="/edit">EditUser</Link>
      </div>
    );
  }
}
