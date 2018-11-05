import React from "react";
export default props => (
  <div className="form-group">
    <select {...props} className="form-control" id={props.name}>
      <option value="">Selecione</option>
      {props.children}
    </select>
  </div>
);
