import React from "react";
export default props => (
  <div>
    <select {...props} className="form-control" id={props.name}>
      <option value="">Selecione</option>
      {props.children}
    </select>
  </div>
);
