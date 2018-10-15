import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Form extends Component {
    render() {
        return (
            <div className={this.props.col}>
                <div className="card">
                    <div style={{ paddingLeft: 40, paddingRight: 55, paddingTop: 44 }}>
                        <h3 className="title-3 m-b-30"><i className={this.props.icon}></i>{this.props.title}</h3>
                    </div>
                    <div className="card-body" style={{ paddingLeft: 40, paddingRight: 55 }}>
                        <div className="row">
                            <div className="col-sm-12">
                                <form onSubmit={this.props.onSubmit}>
                                    {this.props.children}
                                    <div className="form-actions form-group">
                                        <button
                                            disabled={this.props.disabled}
                                            className="btn btn-primary">Salvar
                                        </button> &nbsp;
                                        <Link to={this.props.urlCancel} className="btn btn-secondary">Cancelar</Link>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Form;