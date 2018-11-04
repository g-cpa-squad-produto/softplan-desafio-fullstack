import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import InputHorizontal from "../common/form/InputHorizontal";
import IconButton from "../common/layout/IconButton";
import { reduxForm, Field, formValueSelector } from "redux-form";

import { init } from "./processoActions";

import ProcessoAprovador from "./processoAprovador";

class ProcessoForm extends Component {
  render() {
    const { handleSubmit, readOnly, usuariosParecer } = this.props;

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
                required
              />
              <div className="form-group">
                <label
                  htmlFor="statusParecer"
                  className="col-sm-2 control-label"
                >
                  Situação
                </label>
                <div className="col-sm-10">
                  <Field name="statusParecer" component="select">
                    <option value="">Selecione</option>
                    <option value="0">Pendente</option>
                    <option value="1">Concluído</option>
                  </Field>
                </div>
              </div>
            </div>
            <ProcessoAprovador list={usuariosParecer} />
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
