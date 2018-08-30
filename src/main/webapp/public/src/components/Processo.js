import React, { Component } from 'react';
import $ from 'jquery';
import InputCustom from './InputCustom';
import PubSub from 'pubsub-js';
import ErrorTreatment from '../utils/ErrorTreatment';

export default class ProcessoBox extends Component {

    constructor () {
        super();
        this.state = { processos : [], usuarios: [] };
    }

    componentDidMount() {
        $.ajax({
            url: 'http://localhost:8080/api/processo',
            dataType: 'json',
            success: function(response) {
                this.setState({ processos: response });
            }.bind(this)
        });
    }

    render() {
        return (
            <div>
                <div className="header">
                    <h1>Cadastro de processos</h1>
                </div>
                <div className="content mg-top-10">
                    <ProcessoForm processos={this.state.processos} />
                    <ProcessoTable />
                </div>
            </div>
        );
    }
}

class ProcessoForm extends Component {

    constructor() {
        super();
        this.state = { numero: '', parecer: '', usuariosAdicionados: [], usuarios: [] };
        this.sendForm = this.sendForm.bind(this);
        this.cleanFields = this.cleanFields.bind(this);
        this.adicionaUsuario = this.adicionaUsuario.bind(this);
        this.removeUsuario = this.removeUsuario.bind(this);
    }

    componentWillMount() {
        $.ajax({
            url: 'http://localhost:8080/api/usuario',
            dataType: 'json',
            success: function(response) {
                this.setState({ usuarios: response });
            }.bind(this)
        });
    }

    setFieldValue(inputName, event) {
        var fieldChanged = {};
        fieldChanged[inputName] = event.target.value;
        this.setState(fieldChanged);
    }

    cleanFields() {
        this.setState({ 
                numero: '',
                parecer: '',
                usuariosAdicionados: []
        });
    }

    adicionaUsuario(codigoUsuario) {
        
        var usuarios = this.state.usuariosAdicionados;

        if (this.state.usuariosAdicionados.filter((usuario) => usuario.codigo == codigoUsuario).length > 1) 
            return;

        var usuarioSelected = this.state.usuarios.filter((usuario) => usuario.codigo == codigoUsuario);
        
        usuarios.push(usuarioSelected[0]);
        
        this.state.usuarios.splice(usuarios.findIndex(u => u.codigo == usuarioSelected.codigo), 1);

        this.setState({ usuariosAdicionados: usuarios });
    }

    removeUsuario(usuarioSelected) {
        
        var usuarios = this.state.usuariosAdicionados;

        if (usuarios) {
            usuarios.splice(usuarios.findIndex(e => e.codigo == usuarioSelected.codigo), 1);
        } 
        
        this.setState({ usuariosAdicionados: usuarios });
    }

    sendForm(event) {
        event.preventDefault();
    
        var numero = this.state.numero.trim();
        var parecer = this.state.parecer.trim();

        const usuario = JSON.parse(localStorage.getItem('usuario'));

        $.ajax({
          url:`http://localhost:8080/api/processo/${usuario.codigo}`,
          type: 'POST',
          contentType: 'application/json',
          dataType: 'JSON',
          data: JSON.stringify({
            numero: numero,
            parecer: parecer,
            usuarios: this.state.usuariosAdicionados
          }),
          success: function(response) { 
            PubSub.publish('update-list-processos', response);
            this.cleanFields();
          }.bind(this), 
          error: function(err) {
            if (err.status === 400) {
                new ErrorTreatment().publish(err.responseJSON);
            }
          },
          beforeSend: function(){
            PubSub.publish('clean-errors-form-processos', {});
          }      
        });
    }

    render() {

        var usuarios = this.state.usuarios.map(function(usuario) {
            return <option key={usuario.codigo} value={usuario.codigo}>{usuario.nome}</option>;
        });

        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned" method="POST" onSubmit={this.sendForm}>
                
                    <div>
                        <InputCustom id="numero" label="Numero" name="numero" type="text" 
                            value={this.state.numero} onChange={ this.setFieldValue.bind(this, 'numero') } />

                        <InputCustom id="parecer" label="Parecer" name="parecer" type="text" 
                            value={this.state.parecer} onChange={ this.setFieldValue.bind(this, 'parecer') } />

                        <div className="pure-control-group">
                            <label htmlFor="usuario">Usuario</label>
                            <select id="usuario" label="usuario" name="usuario" 
                                value={this.state.usuario} onChange={ this.setFieldValue.bind(this, 'usuario') }>
                                <option>Selecione o Usuário</option> 
                                { usuarios }
                            </select>
                            <button type="button" className="pure-button pure-button-primary" onClick={() => this.adicionaUsuario(this.state.usuario)}>
                                +
                            </button>
                        </div>
                    </div>

                    <div>
                        <span>Usuários adicionados</span>
                        {
                            this.state.usuariosAdicionados.map(function(usuario) {
                                return (
                                    <div key={usuario.codigo}>
                                        <span>{usuario.nome}</span>
                                        <button type="button" className="pure-button pure-button-primary" 
                                            onClick={() => this.removeUsuario(usuario)}>
                                            x
                                        </button>
                                    </div>                               
                                        
                                );
                            }.bind(this))
                        }

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

class ProcessoTable extends Component {

    constructor() {
        super();
        this.state = { processos: [] };
    }

    componentDidMount() {
        $.ajax({
            url: 'http://localhost:8080/api/processo',
            dataType: 'json',
            success: function(response) {
                this.setState({ processos: response });
            }.bind(this)
        });

        PubSub.subscribe('update-list-processos', function(topic, newValue) {

            var processos = this.state.processos;
            processos.push(newValue);
            this.setState({ processos: processos });

        }.bind(this));
    }

    editProcesso(processo) {

        this.setState({numero : processo.numero});


    }

    render () {

        return (
            <div>
                <table className="pure-table">
                    <thead>
                        <tr>
                            <th>Número</th>
                            <th>Status</th>
                            <th>Parecer</th>
                            <th>Usuarios</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.processos.map(function(processo) {
                                return (
                                    <tr key={processo.codigo}>
                                        <td>{processo.numero}</td>
                                        <td>{processo.status}</td>
                                        <td>{processo.parecer}</td>
                                        <td>
                                            {
                                                processo.usuarios.map(function(usuario) {
                                                    return (
                                                        usuario.nome ? (
                                                        <span>{usuario.nome}, </span>) : (<span></span>)
                                                    );
                                                })
                                            }
                                        </td>
                                        <td>                                  
                                            <button type="submit" className="pure-button pure-button-primary" onClick={() => this.editProcesso(processo)}>
                                                Editar
                                            </button>
                                        </td>
                                    </tr>
                                );
                            }.bind(this))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}