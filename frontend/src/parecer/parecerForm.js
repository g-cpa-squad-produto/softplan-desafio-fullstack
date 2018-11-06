import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import InputHorizontal from "../common/form/InputHorizontal";
import Select from "../common/form/Select";
import IconButton from "../common/layout/IconButton";
import { reduxForm, Field, formValueSelector } from "redux-form";

import { init } from "../processo/processoActions";

const required = value =>
  value || typeof value === "number" ? undefined : "Required";
class ProcessoForm extends Component {
  render() {
    const { handleSubmit, readOnly } = this.props;

    return (
      <form onSubmit={handleSubmit}>
        <div className="box box-info">
          <div role="form" className="form-horizontal">
            <div className="box-body">
              <Field
                readOnly={readOnly}
                name="numero"
                component={InputHorizontal}
                label="Número"
                placeholder="Informe o número"
                required
              />

              <Field
                readOnly={readOnly}
                name="descricao"
                component={InputHorizontal}
                label="Descrição"
                placeholder="Informe a descrição"
              />

              <Field
                component={Select}
                id="statusParecer"
                name="statusParecer"
                label="Situação processo"
              >
                <option value="0">Pendente</option>
                <option value="1">Outro</option>
              </Field>

              <Field
                name="descricaoParecer"
                component={InputHorizontal}
                type="text"
                label="Parecer"
                validate={[required]}
                placeholder="Informe o parecer"
              />
            </div>

            <div className="box-footer">
              <div className="pull-right">
                <IconButton
                  stylebutton="primary"
                  label={this.props.submitLabel}
                  type="submit"
                  onClick={this.props.create}
                />
                <IconButton
                  stylebutton="default"
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

ProcessoForm = reduxForm({ form: "processoForm", destroyOnUnmount: false })(
  ProcessoForm
);
const selector = formValueSelector("processoForm");
const mapStateToProps = state => ({
  usuariosParecer: selector(state, "usuariosParecer")
});

const mapDispatchToProps = dispatch => bindActionCreators({ init }, dispatch);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProcessoForm);
