import React from "react";

export default props => (
  <li className="treeview">
    <a href="#/">
      <i className={`fa  fa-${props.icon}`} />
      <span>{props.label}</span>
      <span className="pull-right-container">
        <i className="fa fa-angle-left pull-right" />
      </span>
    </a>

    <ul className="treeview-menu">{props.children}</ul>
  </li>
);
