import React from "react";
import { reduxForm, Field } from "redux-form";
import { SimpleInput } from "../../components/SimpleInput";
import { PinkButtom } from "../../components/Buttons";

const validate = values => {
    const errors = {};

    if (!values.email) {
        errors.email = true;
    }

    if (!values.senha) {
        errors.senha = true;
    }

    return errors;
};

const LoginForm = ({ handleSubmit, isLoading }) => {
    
    return (
        <form onSubmit={handleSubmit}>
            <div className="field">
                <label className="label">Digite seu e-mail</label>
                <div className="control">
                    <Field
                        name="email"
                        component={SimpleInput}
                        type="email"
                        className="animated fadeIn"
                        maxLength={30}
                    />
                </div>
            </div>

            <div className="field">
                <label className="label">Digite sua senha</label>
                <div className="control">
                    <Field
                        name="senha"
                        component={SimpleInput}
                        type="password"
                        className="animated fadeIn"
                        maxLength={30}
                    />
                </div>
            </div>

            <div className="field">
                <label className="checkbox" />
            </div>

            <PinkButtom type="submit" isLoading={isLoading}>Login</PinkButtom>
        </form>
    );
};

export default reduxForm({
    form: "login",
    validate
})(LoginForm);
