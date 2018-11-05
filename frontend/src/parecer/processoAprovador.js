import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { formValueSelector, Field, arrayInsert, arrayRemove } from "redux-form";
import Grid from "../common/layout/Grid";
import Input from "../common/form/Input";

class AprovadorList extends Component {
  aprovadores() {
    const finalizadores = this.props.listFinalizadores || [];
    return (
      <tr>
        <td>
          <Field component="select" name="finalizador">
            <option value="">Selecione</option>
            {finalizadores.map((item, i) => (
              <option key={i} value={item.login}>
                {item.nome}
              </option>
            ))}
          </Field>
        </td>
        <td>
          <button
            type="button"
            className="btn btn-success"
            onClick={() => this.add(this.props.list.length)}
          >
            <i className="fa fa-plus" />
          </button>
        </td>
      </tr>
    );
  }

  add(index) {
    debugger;
    const finalizadorSelecionado = this.props.finalizador;
    const item = this.props.listFinalizadores.find(
      param => param.login === finalizadorSelecionado
    );

    if (!this.props.readOnly) {
      this.props.arrayInsert("processoForm", "usuariosParecer", index, item);
    }
  }

  remove(index) {
    if (!this.props.readOnly && this.props.list.length > 1) {
      this.props.arrayRemove("processoForm", "usuariosParecer", index);
    }
  }

  renderRows() {
    const list = this.props.list || [];
    return list.map((item, index) => (
      <tr key={index}>
        <td>
          <Field
            name={`usuariosParecer[${index}].nome`}
            component={Input}
            placeholder="Informe o nome"
            readOnly={true}
          />
        </td>
        <td>
          <button
            type="button"
            className="btn btn-danger"
            onClick={() => this.remove(index)}
          >
            <i className="fa fa-trash-o" />
          </button>
        </td>
      </tr>
    ));
  }

  render() {
    return (
      <Grid cols={this.props.cols}>
        <fieldset>
          <legend>{this.props.legend}</legend>

          <table className="table">
            <thead>
              <tr>
                <th>Finalizadores</th>
                <th className="table-actions">Ações</th>
              </tr>
            </thead>
            <tbody>
              {this.aprovadores()}
              {this.renderRows()}
            </tbody>
          </table>
        </fieldset>
      </Grid>
    );
  }
}

const mapDispatchToProps = dispatch =>
  bindActionCreators({ arrayInsert, arrayRemove }, dispatch);

const selector = formValueSelector("processoForm");
const mapStateToProps = state => ({
  listFinalizadores: state.usuario.listFinalizadores,
  finalizador: selector(state, "finalizador")
});
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AprovadorList);