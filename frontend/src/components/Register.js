import React, {Component} from 'react';
import {connect} from 'react-redux';
import PropTypes from 'prop-types';
import {withRouter} from 'react-router-dom';
import {postAction} from '../actions/userAction';
import classnames from 'classnames';
import {Button, ButtonGroup} from "reactstrap";

class Register extends Component {

    constructor() {
        super();
        this.state = {
            name: '',
            email: '',
            password: '',
            role: '',
            rSelected: 0,
            errors: {}
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleBack = this.handleBack.bind(this);
        this.onRadioBtnClick = this.onRadioBtnClick.bind(this);
    }

    handleBack() {
        this.props.history.push("/")
    }

    handleInputChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onRadioBtnClick(roleId) {
        this.setState({role: roleId})
    }

    handleSubmit(e) {
        e.preventDefault();
        const user = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
            roleId: this.state.role,
        }
        this.props.postAction(localStorage.getItem('role'), user);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.main.post.content) {
            this.props.history.push("/")
        }
        if(nextProps.main){

        }
        if (nextProps.errors) {
            this.setState({
                errors: nextProps.errors
            });
        }
    }

    componentDidMount() {
        if (this.props.location.state) {
            this.setState(this.props.location.state.user)
            this.setState({rSelected: this.props.location.state.user.roleId})
        }
    }

    render() {
        const {errors} = this.state;
        return (
            <div className="container" style={{marginTop: '50px', width: '700px'}}>
                <h3>
                    {
                        this.state.id && ('Editar ')
                    }
                    {
                        this.state.id === undefined && ('Cadastrar ')
                    }
                    usuário
                </h3>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <Button outline onClick={this.handleBack}>Voltar</Button>
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="Nome"
                            className={classnames('form-control form-control-lg', {
                                'is-invalid': errors.name
                            })}
                            name="name"
                            onChange={this.handleInputChange}
                            value={this.state.name}
                        />
                        {errors.name && (<div className="invalid-feedback">{errors.name}</div>)}
                    </div>
                    <div className="form-group">
                        <input
                            type="email"
                            placeholder="Email"
                            className={classnames('form-control form-control-lg', {
                                'is-invalid': errors.email
                            })}
                            name="email"
                            onChange={this.handleInputChange}
                            value={this.state.email}
                        />
                        {errors.email && (<div className="invalid-feedback">{errors.email}</div>)}
                    </div>
                    <div className="form-group">
                        <input
                            type="password"
                            placeholder="Senha"
                            className={classnames('form-control form-control-lg', {
                                'is-invalid': errors.password,
                            })}
                            name="password"
                            onChange={this.handleInputChange}
                            value={this.state.password}
                        />
                        {errors.password && (<div className="invalid-feedback">{errors.password}</div>)}
                    </div>
                    <div className="form-group">
                        <ButtonGroup>
                            <Button outline color="secondary" onClick={() => this.onRadioBtnClick(1)}
                                    active={this.state.rSelected === 1}>Administrador</Button>
                            <Button outline color="secondary" onClick={() => this.onRadioBtnClick(2)}
                                    active={this.state.rSelected === 2}>Triador</Button>
                            <Button outline color="secondary" onClick={() => this.onRadioBtnClick(3)}
                                    active={this.state.rSelected === 3}>Finalizador</Button>
                        </ButtonGroup>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary">
                            {
                                this.state.id && ('Editar')
                            }
                            {
                                this.state.id === undefined && ('Cadastrar')
                            }
                        </button>


                    </div>
                </form>
            </div>
        )
    }
}

Register.propTypes = {
    postAction: PropTypes.func.isRequired,
    main: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    main: state.main,
    errors: state.errors
});

export default connect(mapStateToProps, {postAction})(withRouter(Register))
