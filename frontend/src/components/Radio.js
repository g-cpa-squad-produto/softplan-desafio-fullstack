import * as React from 'react';
import { Field } from 'redux-form';

export const Radio = props => {
  if (props && props.input && props.options) {
    const renderRadioButtons = (key, index) => {
      return (
        <label className="sans-serif w-100" key={`${index}`} htmlFor={`${props.input.name}-${index}`}>
          <Field
            id={`${props.input.name}${key}`}
            component="input"
            name={props.input.name}
            type="radio"
            value={key}
            className="mh2"
          />
          {props.options[key]}
        </label>
      )
    };
    return (
      <div className="mv3 w-100">
        <div className="b sans-serif pv2 w-100">
          {props.label}
        </div>
        <div>
          {props.options &&
            Object.keys(props.options).map(renderRadioButtons)}
        </div>
      </div>
    );
  }
  return <div></div>
}

export default Radio;
