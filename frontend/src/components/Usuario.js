import React, {Component} from 'react';
import $ from 'jquery';
import InputCustom from './InputCustom';
import PubSub from 'pubsub-js';
import ErrorTreatment from '../utils/ErrorTreatment';

export default class AutorBox extends Component {

    constructor() {
        super();
        this.state = { usuarios: [], perfis: [] };
    }

    componentDidMount() {
        $.ajax({
            url: 'http://localhost:8080/api/usuario/perfil',
            dataType: 'json',
            success: function(response) {
                this.setState({ perfis: response });
            }.bind(this)
        });
    }

    render() {
        return (
            <div>
                <div className="header">
                    <h1>Cadastro de usuarios</h1>
                </div>
                <div className="content mg-top-10">
                    <UsuarioForm perfis={this.state.perfis} />
                    <UsuarioTable />
                </div>
            </div>
        );
    }
}

class UsuarioForm extends Component {
    
    constructor() {
        super();
        this.state = {nome: '', perfil: ''};
        this.sendForm = this.sendForm.bind(this);
        this.cleanFields = this.cleanFields.bind(this);
    }

    sendForm(event) {
        event.preventDefault();
    
        $.ajax({
          url:'http://localhost:8080/api/usuario',
          type: 'POST',
          contentType: 'application/json',
          dataType: 'JSON',
          data: JSON.stringify(
            {
              nome: this.state.nome, 
              perfil: this.state.perfil
            }
          ),
          success: function(response) { 
          
            PubSub.publish('update-list-usuarios', response);
            this.cleanFields();
          
          }.bind(this), 
          error: function(err) {
            if (err.status === 400) {
                new ErrorTreatment().publish(err.responseJSON);
            }
          },
          beforeSend: function(){
            PubSub.publish('clean-errors-form-usuarios', {});
          }      
        });
    }

    setFieldValue(inputName, event) {
        var fieldChanged = {};
        fieldChanged[inputName] = event.target.value;
        this.setState(fieldChanged)
    }

    cleanFields() {
        this.setState({ nome: '', perfil: '' });
    }
      
    render() {
        
        var perfis = this.props.perfis.map(function(perfil) {
            return <option key={perfil.name} value={perfil.name}>{perfil.descricao}</option>;
        });

        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned" method="POST" onSubmit={this.sendForm}>
                
                    <InputCustom id="nome" label="Nome" name="nome" type="text" 
                        value={this.state.nome} onChange={this.setFieldValue.bind(this, 'nome')} />

                    <div className="pure-control-group">
                        <label htmlFor="perfil">Perfil</label>
                        <select id="perfil" label="Perfil" name="perfil" 
                            value={this.state.perfil} onChange={ this.setFieldValue.bind(this, 'perfil') }>
                            <option>Selecione o Perfil</option> 
                            { perfis }
                        </select>
                    </div>

                    <div className="pure-control-group">                                  
                        <label></label> 
                        <button type="submit" className="pure-button pure-button-primary">Gravar</button>                                    
                    </div>
                </form>             
            </div> 
        );
    }
}

class UsuarioTable extends Component {
    
    constructor() {
        super();
        this.state = { usuarios: [] };
    }

    componentDidMount() {
        $.ajax({
            url: 'http://localhost:8080/api/usuario',
            dataType: 'json',
            success: function(response) {
                this.setState({ usuarios: response });
            }.bind(this)
        });

        PubSub.subscribe('update-list-usuarios', function(topic, newValue) {

            var usuarios = this.state.usuarios;
            usuarios.push(newValue);
            this.setState({ usuarios: usuarios });

        }.bind(this));
    }

    render() {
        return (
            <div>            
                <table className="pure-table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Perfil</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.usuarios.map(function(usuario) {
                                return (
                                    <tr key={usuario.codigo}>
                                        <td>{usuario.nome}</td>                
                                        <td>{usuario.perfil.descricao}</td>                
                                    </tr>
                                );
                            })
                        }
                    </tbody>
                </table> 
            </div>  
        );
    }
}