import React, { Component } from 'react'
import axios from 'axios'
import api from '../api'
import UsuarioForm from './usuarioForm'
import UsuarioList from './usuarioList' 



const URL = 'usuarios'

export default class Usuario extends Component {

  constructor(props){
    super(props)
    this.state = {id: '', nome: '', login: '', senha:'', tipo:'', edit:false}    
    
    this.handleAdd = this.handleAdd.bind(this)
    this.setNome = this.setNome.bind(this)
    this.setLogin = this.setLogin.bind(this)
    this.setSenha = this.setSenha.bind(this)
    this.setTipo = this.setTipo.bind(this)

    this.handleClear = this.handleClear.bind(this)
    this.handleList = this.handleList.bind(this)
    this.handleRemove = this.handleRemove.bind(this)
    this.handlePrepareUpdate = this.handlePrepareUpdate.bind(this);
    this.handlePrepareInsert = this.handlePrepareInsert.bind(this);

  }


  componentDidMount() {
    axios({
      method: 'get', //you can set what request you want to be
      url: `${api.API_URL}${URL}`,
      headers: {
        token: 'bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBRE1JTiJ9.KM7Q0IaI4DjqAw0qY0YNzqUwlmSzRgyp5DIC4Iq_vnY'
      }
    }).then(resp => this.console.log(resp))
    .catch(e => {
      console.log('Error: ', e.response)
    })
  }

  

  handleRemove(usuario){
    axios.delete(`${URL}${usuario.id}`)
    .then(resp => this.refresh())
  }

  handleAdd(){
    axios.post(URL, {nome:this.state.nome, login:this.state.login, senha:this.state.senha})
      .then(resp => this.refresh())
  }  

  handleClear() {
    this.setState ({...this.state, id: '', nome: '', login: '', senha:'', tipo:''})  
  }

  setNome(e){
    this.setState({...this.state, nome: e.target.value})
  }

  setLogin(e){
    this.setState({...this.state, login: e.target.value})
  }

  setSenha(e){
    this.setState({...this.state, senha: e.target.value})
  }

  setTipo(e){
    this.setState({...this.state, tipo: e.target.value})
  }

  handlePrepareUpdate(e){
    this.setState({...this.state, id: e.id
                  , nome: e.nome
                  , login: e.login
                  , senha: e.senha
                  , tipo : e.tipo
                  , edit : true})
  }

  handlePrepareInsert(){
    this.setState({...this.state, edit : true})
  }
  
  handleList(){
    this.setState({...this.state, edit : false})
  }

  render() {
      return (
        <div>
              <UsuarioForm 
                nome={this.state.nome} 
                login={this.state.login} 
                senha={this.state.senha} 
                tipo={this.state.tipo}

                setNome={this.setNome}
                setLogin={this.setLogin}
                setSenha={this.setSenha}
                setTipo={this.setTipo}

                handleAdd={this.handleAdd}
                handleClear={this.handleClear}
                edit={this.state.edit}
                handleList={this.handleList}
                /> 
              
              <UsuarioList
                edit={this.state.edit}
                handlePrepareInsert={this.handlePrepareInsert} 
                handleRemove={this.handleRemove}
                handlePrepareUpdate={this.handlePrepareUpdate} 
                /> 
        </div>
      )
    }
}
  
