import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class ProcessesList extends Component {
  render() {
    return (
      <div class="ProcessesList">
        ProcessesList
        <Link to="/add">AddProcess</Link>
      </div>
    );
  }
}
