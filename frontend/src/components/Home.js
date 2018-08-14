import React, { Component } from 'react';
import Moment from 'moment';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { getAction } from '../actions/userAction';

class Home extends Component {

    constructor() {
        super();
        this.state = {
            user: {},
            main: {},
            get: [],
            errors: {}
        }
    }

    componentDidMount() {
        this.props.getAction(localStorage.getItem('role'));
    }
    componentWillReceiveProps(nextProps) {
        if (nextProps.auth.user) {
            this.setState({
                user: nextProps.auth.user
            });
        }
        if (nextProps.main) {
            this.setState({
                main: nextProps.main
            });
        }
        if (nextProps.main.get.content) {
            this.setState({
                users: nextProps.main.get.content
            });
        }
    }


    render() {
        const { users } = this.state;
        let rows;
        if (users) {
            rows = users.map((item, i) => {
                return (<tr key={i} >
                    <td>{item.name}</td>
                    <td>{item.email}</td>
                    <td>{item.role.name}</td>
                    <td>{Moment(item.createdAt).format('HH:mm DD/MM/YYYY')}</td>
                </tr>)
            });
        }
        return (
            <div>
                <h3>Usuários</h3>
                <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Email</th>
                        <th scope="col">Permissão</th>
                        <th scope="col">Data de Criação</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rows}
                    </tbody>
                </table>
            </div>
        );
    }
}

Home.propTypes = {
    getAction: PropTypes.func.isRequired,
    user: PropTypes.object,
    main: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    auth: state.auth,
    main: state.main,
    errors: state.errors
})


export default connect(mapStateToProps, { getAction })(Home)
