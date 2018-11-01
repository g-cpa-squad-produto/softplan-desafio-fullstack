import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import InputHorizontal from "../common/form/InputHorizontal";
import Select from "../common/form/Select";
import IconButton from "../common/layout/IconButton";
import { reduxForm, Field } from "redux-form";

import { init } from "./usuarioActions";

class UsuarioForm extends Component {
  render() {
    const { handleSubmit, readOnly } = this.props;
    return (
      <form onSubmit={handleSubmit}>
        <div className="box box-info">
          <div role="form" className="form-horizontal">
            <div className="box-body">
              <Field
                readOnly={readOnly}
                name="nome"
                component={InputHorizontal}
                label="Nome"
                placeholder="Informe o nome"
                required
              />

              <Field
                readOnly={readOnly}
                name="login"
                component={InputHorizontal}
                label="Login"
                placeholder="Informe o nome"
                required
              />

              <Field
                readOnly={readOnly}
                name="senha"
                component={InputHorizontal}
                label="Senha"
                type="password"
                placeholder="Informe a senha"
              />

              <Field
                readOnly={readOnly}
                name="tipoUsuario"
                component={Select}
                label="Tipo"
              >
                <option value="ADMINISTRADOR">Administrador</option>
                <option value="TRIADOR">Triador</option>
                <option value="FINALIADOR">Finalizador</option>
              </Field>
            </div>
            <div className="box-footer">
              <div className="pull-right">
                <IconButton
                  styleButton="primary"
                  label={this.props.submitLabel}
                  type="submit"
                  onClick={this.props.create}
                />
                <IconButton
                  styleButton="default"
                  label="Cancelar"
                  onClick={this.props.init}
                  type="button"
                />
              </div>
            </div>
          </div>
        </div>
      </form>
    );
  }
}

UsuarioForm = reduxForm({ form: "usuarioForm", destroyOnUnmount: false })(
  UsuarioForm
);
const mapDispatchToProps = dispatch => bindActionCreators({ init }, dispatch);
export default connect(
  null,
  mapDispatchToProps
)(UsuarioForm);
