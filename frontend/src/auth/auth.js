import './auth.css'
import React, { Component } from 'react'
import { reduxForm, Field } from 'redux-form'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { login, signup } from './authActions'
import Row from '../common/layout/Row'
import Grid from '../common/layout/Grid'
import Messages from '../common/msg/messages'
import Input from '../common/form/inputAuth'

class Auth extends Component {
    constructor(props) {
        super(props)
        this.state = { loginMode: true }
    }

    changeMode() {
        this.setState({ loginMode: !this.state.loginMode })
    }

    onSubmit(values) {
        const { login, signup } = this.props
        this.state.loginMode ? login(values) : signup(values)
    }

    render() {
        const { handleSubmit } = this.props
        return (
            <div className="login-box">
                <div className="login-logo"><b> Softplan</b> </div>
                <div className="login-box-body">
                    <p className="login-box-msg">Bem vindo!</p>
                    <form onSubmit={handleSubmit(v => this.onSubmit(v))}>
                        <Field component={Input} type="login" name="login"
                            placeholder="Usuário" icon='user'/>
                        <Field component={Input} type="password" name="senha"
                            placeholder="Senha" icon='lock' />
                        <Row>
                            <Grid cols="4">
                                <button type="submit" className="btn btn-primary btn-block btn-flat">
                                    Entrar
                                </button>
                            </Grid>
                        </Row>
                    </form>
                    <br />
                </div>
                <Messages />
            </div>
        )
    }
}

Auth = reduxForm({ form: 'authForm' })(Auth)
const mapDispatchToProps = dispatch => bindActionCreators({ login, signup }, dispatch)
export default connect(null, mapDispatchToProps)(Auth)