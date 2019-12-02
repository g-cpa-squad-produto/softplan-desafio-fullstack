import React, { Component } from 'react'

import api from './Api'
import { Redirect } from 'react-router-dom'

const tipo = {
    'administrador': 'Administrador',
    'triador': 'Triador',
    'finalizador': 'Finalizador'
}


class EditUsuario extends Component {
    
    constructor(props) {
        super(props)

        this.state = {
            isLoading: false,
            redirect: false,
            usuario: {}
        }
        this.saveusuario = this.saveusuario.bind(this)
    }

    componentDidMount() {
        this.setState({ isLoading: true })
        api.loadusuarioById(this.props.match.params.id)
        .then((res)=>{
            this.setState({usuario:res.data})
            this.refs.name.value= this.state.usuario.name
            this.refs.email.value= this.state.usuario.email
            this.refs.senha.value= this.state.usuario.senha
            this.refs.tipo.value= this.state.usuario.tipo

        })
    }

    saveusuario() {
        const newusuario = {
            id: this.props.match.params.id,
            name: this.refs.name.value,
            email: this.refs.email.value,
            senha: this.refs.senha.value,
            tipo: this.refs.tipo.value
        }
        api.updateusuario(newusuario).then(
            (res) => {
                this.setState({
                    redirect: '/usuario/' + this.refs.genre.value
                })
            }

        )
    }

    render() {
        return (
            <section id="intro" className="intro-section">
                {this.state.redirect &&
                    <Redirect to={this.state.redirect} />
                }
                <h1>Editar série </h1>
                <form>
                    Nome: <input ref="name" type="text" className="form-control" /><br />
                    Email: <input ref="email" type="text" className="form-control" /><br />
                    Senha: <input ref="senha" type="text" className="form-control" /><br />
                    Tipo: <select ref="tipo">
                        {Object.keys(tipo).map(
                            key => <option key={key} value={key}>{tipo[key]}</option>
                        )}
                    </select> <br />
                </form>
            </section>
        )
    }

}

export default EditUsuario