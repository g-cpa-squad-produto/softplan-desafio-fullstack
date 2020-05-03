import React, {Component} from 'react';
import api from '../../services/api'

export default class UsuarioDados extends Component {
    state = {
        usuario: {}
    }

    async componentDidMount() {
        const {id} = this.props.match.params;
        const response = await api.get(`/usuario/${id}`)
        this.setState({ usuario: response.data })                
    }

    render() {
        const { usuario } = this.state;
        return (
            <form>
                <input type="hidden" name="usuarioId" id="usuarioId" value={usuario.usuarioId}/>
                <div className="form-group">
                    <label for="nome">Nome e Sobrenome</label>
                    <input type="text" className="form-control" name="nome" id="nome" placeholder="Seu nome e sobrenome" value={usuario.nome}/>
                </div>
                <div className="form-group">
                    <label for="email">Endereço de email</label>
                    <input type="email" className="form-control" name="email" id="email" placeholder="Seu email" value={usuario.email}/>
                </div>
                <div className="form-group">
                    <label for="dataNascimento">Date de Nascimento</label>
                    <input type="date" className="form-control" name="dataNascimento" id="dataNascimento" value={usuario.dataNascimento}/>
                </div>
                <button type="submit" className="btn btn-primary">Salvar</button>
            </form>
        )
    }
}