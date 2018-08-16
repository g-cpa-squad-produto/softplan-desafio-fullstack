import React, {Component} from 'react';
import Moment from 'moment';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAction, deleteAction} from '../actions/userAction';
import {Button, ButtonGroup} from "reactstrap";

class Home extends Component {

    constructor() {
        super();
        this.state = {
            user: {},
            main: {},
            get: [],
            errors: {}
        }

        this.createUser = this.createUser.bind(this);
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

    deleteUser(deleteUser) {
        this.props.deleteAction(localStorage.getItem('role'), deleteUser.id);
    }

    editUser(item) {
        this.props.history.push({
            pathname: '/register',
            state: { user: item}
        })
    }

    createUser(){
        this.props.history.push('/register')
    }

    render() {
        const {users} = this.state;
        let rows;
        if (users) {
            rows = users.map((item, i) => {
                return (<tr key={i}>
                    <td>{item.name}</td>
                    <td>{item.email}</td>
                    <td>{item.role.name}</td>
                    <td>{Moment(item.createdAt).format('HH:mm DD/MM/YYYY')}</td>
                    <td>
                        <ButtonGroup>
                            <Button outline color="secondary"  onClick={() => this.editUser(item)}>Editar</Button>
                            <Button outline  color="danger" onClick={() => this.deleteUser(item)}>Deletar</Button>
                        </ButtonGroup>
                    </td>
                </tr>)
            });
        }
        return (
            <div>
                <div className="row mt-4 mb-4">
                    <div className="col"><h3>Usuários</h3></div>
                    <div className="col text-right">
                        <Button color="primary" onClick={this.createUser}>Criar Usuario</Button>
                    </div>
                </div>

                <table className="table table-bordered table-hover table-sm">
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Email</th>
                        <th scope="col">Permissão</th>
                        <th scope="col">Data de Criação</th>
                        <th scope="col text-center">Ação</th>
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
    deleteAction: PropTypes.func.isRequired,
    user: PropTypes.object,
    main: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    auth: state.auth,
    main: state.main,
    errors: state.errors
})


export default connect(mapStateToProps, {getAction, deleteAction})(Home)
