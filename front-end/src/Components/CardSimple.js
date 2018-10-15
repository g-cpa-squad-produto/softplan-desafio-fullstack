import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import {hasPermission} from "../Methods";

class CardSimple extends Component {
    render() {
        return (
            <div className={this.props.col}>
                <div className={"user-data m-b-30 " + this.props.class}>
                    {this.props.title
                        ? <h3 className="title-3 m-b-30">
                            <i className={this.props.icon}></i>{this.props.title}
                            {(hasPermission('ROLE_PROCESS_GRANT_ALL'))
                                ? <Link to={this.props.urlNew} className="btn btn-primary pull-right"><i className="fa fa-edit"></i>CADASTRAR</Link>
                                : null}
                        </h3>
                        : null}

                    {this.props.content}
                    <div className="table-responsive table-data">
                        {this.props.children}
                    </div>
                </div >
            </div >
        );
    }
}

export default CardSimple;