import React, { Component } from "react";
import Menu from "./Menu";

export default class SideBar extends Component {
  render() {
    return (
      <aside className="main-sidebar">
        <section className="sidebar">
          <Menu />
        </section>
      </aside>
    );
  }
}
