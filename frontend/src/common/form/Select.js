import React from "react";
export default props => (
  <div className="form-group">
    <label htmlFor={props.id} className="col-sm-2 control-label">
      {props.label}
    </label>
    <div className="col-sm-10">
      <select {...props} className="form-control">
        <option value="">Selecione</option>
        {props.children}
      </select>
    </div>
  </div>
);
