import React from "react";

const makeInput = props => ({ input, meta, className, ...rest }) => (
    <input
        {...input}
        {...rest}
        {...props}
        className={`input ${props.className} ${className} 
            ${meta.error && meta.touched && "is-danger"}`}
    />
);

const SimpleInput = makeInput({});

export { makeInput, SimpleInput };
