import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { getList, showUpdate, showDelete } from "./usuarioActions";

class UsuarioList extends Component {
  componentWillMount() {
    this.props.getList();
  }

  renderRows() {
    const list = this.props.list || [];
    return list.map(usuario => (
      <tr key={usuario.id}>
        <td>{usuario.nome}</td>
        <td>
          <button
            className="btn btn-warning"
            onClick={() => this.props.showUpdate(usuario)}
          >
            <i className="fa fa-pencil" />
          </button>
          <button
            className="btn btn-danger"
            onClick={() => this.props.showDelete(usuario)}
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
                <th>Nome</th>
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

const mapStateToProps = state => ({ list: state.usuario.list });
const mapDispatchToProps = dispatch =>
  bindActionCreators({ getList, showUpdate, showDelete }, dispatch);
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UsuarioList);
