import React from "react";
import If from "../operator/If";

export default props => (
  <If test={!props.hide}>
    <button
      {...props}
      className={"btn btn-" + props.styleButton}
      onClick={props.onClick}
      type={props.type}
    >
      <i className={"fa fa-" + props.icon} />
      {props.label}
    </button>
  </If>
);
