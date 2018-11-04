import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { Field, arrayInsert, arrayRemove } from "redux-form";
import Grid from "../common/layout/Grid";
import Select from "../common/form/Select";
import Input from "../common/form/Input";

class AprovadorList extends Component {
  add(index, item = {}) {
    if (!this.props.readOnly) {
      this.props.arrayInsert("processoForm", this.props.field, index, item);
    }
  }

  remove(index) {
    if (!this.props.readOnly && this.props.list.length > 1) {
      this.props.arrayRemove("processoForm", this.props.field, index);
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
                <th>Aprovador</th>
                <th className="table-actions">Ações</th>
              </tr>
            </thead>
            <tbody>{this.renderRows()}</tbody>
          </table>
        </fieldset>
      </Grid>
    );
  }
}

const mapDispatchToProps = dispatch =>
  bindActionCreators({ arrayInsert, arrayRemove }, dispatch);
const mapStateToProps = state => ({
  usuario: state.usuario
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AprovadorList);
