import React from "react";
import { Field } from "redux-form";
export default props => (
  <div className="form-group">
    <label htmlFor={props.id} className="col-sm-2 control-label">
      {props.label}
    </label>
    <div className="col-sm-10">
      <Field {...props} name="favoriteColor" component="select">
        <option value="">Selecione</option>
        {props.children}
      </Field>
    </div>
  </div>
);
