import React, {Component} from 'react';
import api from '../../services/api'

export default class UsuarioDados extends Component {
    state = {
        nome: "",
        email: "",
        dataNascimento: ""
    }
    
    async handleSubmit() {
        const nome = this.state.nome;
        const email = this.state.email;
        const dataNascimento = this.state.dataNascimento;
        const data = `{"nome": "${nome}", "email", "${email}", "dataNascimento": "${dataNascimento}" }`
        const response = await api.post(`/usuario`, data)
        console.log(response)
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>                
                <div className="form-group">
                    <label >Nome e Sobrenome</label>
                    <input type="text" className="form-control" name="nome" id="nome" placeholder="Seu nome e sobrenome"/>
                </div>
                <div className="form-group">
                    <label >Endereço de email</label>
                    <input type="email"  className="form-control" name="email" id="email" placeholder="Seu email" />
                </div>
                <div className="form-group">
                    <label >Date de Nascimento</label>
                    <input type="date"  className="form-control" name="dataNascimento" id="dataNascimento" />
                </div>
                <button type="submit" className="btn btn-primary">Cadastrar</button>
            </form>
        )
    }
}