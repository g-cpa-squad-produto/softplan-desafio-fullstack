import React from "react";
import If from "../operator/If";

export default props => (
  <If test={!props.hide}>
    <div className="form-group has-feedback">
      <input
        {...props.input}
        className="form-control"
        placeholder={props.placeholder}
        readOnly={props.readOnly}
        type={props.type}
      />
      <span
        className={`glyphicon glyphicon-${props.icon}
                form-control-feedback`}
      />
    </div>
  </If>
);
