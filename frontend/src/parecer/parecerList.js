import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { getList, showUpdate, showDelete } from "../processo/processoActions";

class ParecerList extends Component {
  renderRows() {
    const list = this.props.list || [];
    return list.map(processo => (
      <tr key={processo.id}>
        <td>{processo.numero}</td>
        <td>{processo.descricao}</td>
        <td>{processo.statusParecer === 0 ? "Pendente" : "Concluído"}</td>
        <td>
          <button
            className="btn btn-warning"
            onClick={() => this.props.showUpdate(processo)}
          >
            <i className="fa fa-pencil" />
          </button>
          <button
            className="btn btn-danger"
            onClick={() => this.props.showDelete(processo)}
          >
            <i className="fa fa-trash-o" />
          </button>
        </td>
      </tr>
    ));
  }

  render() {
    return (
      <div className="box">
        <div className="box-body">
          <table className="table table-bordered">
            <tbody>
              <tr>
                <th>Número</th>
                <th>Descrição</th>
                <th>Situação</th>
                <th className="table-actions">Ações</th>
              </tr>
              {this.renderRows()}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({ list: state.processo.list });
const mapDispatchToProps = dispatch =>
  bindActionCreators({ getList, showUpdate, showDelete }, dispatch);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParecerList);
