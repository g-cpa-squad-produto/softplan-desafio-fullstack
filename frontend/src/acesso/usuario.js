import React, { Component } from 'react'
import axios from 'axios'

import ContentHeader from '../components/ContentHeader'
import Content from '../components/Content'
import Row from '../components/Row'
import UsuarioForm from './usuarioForm'
import UsuarioList from './usuarioList' 



const URL = 'http://localhost:8000/v1/cargo/'

export default class Usuario extends Component {

  constructor(props){
    super(props)
    this.state = {id:'', descricao:'', list: []}
    
    this.handleAdd = this.handleAdd.bind(this)
    this.handleChange = this.handleChange.bind(this)
    this.handleSearch = this.handleSearch.bind(this) 
    this.handleClear = this.handleClear.bind(this)

    this.handleRemove = this.handleRemove.bind(this)
    this.handlePrepareUpdate = this.handlePrepareUpdate.bind(this)

    this.refresh()
  }


  refresh(descricao = ''){
    const search = descricao ? `?nome=${descricao}` : ''
    axios.get(`${URL}${search}`)
      .then(resp => 
        this.setState({...this.state, descricao, list : resp.data}))
  }
  
  handleRemove(usuario){
    axios.delete(`${URL}${usuario.id}`)
    .then(resp => this.refresh())
  }

  handleAdd(){
    const descricao = this.state.descricao
    axios.post(URL, {nome: descricao, descricao: descricao})
      .then(resp => this.refresh())
  }  

  handleClear() {
    this.refresh()
  }

  handleChange(e){
    this.setState({...this.state, descricao: e.target.value})
  }

  handlePrepareUpdate(usuario){
    this.setState({...this.state, id: usuario.id, descricao: usuario.descricao})
  }

  handleSearch(){
    this.refresh(this.state.descricao)
  }

  render() {
      return (
        <div>
          <ContentHeader title="Usuario" subTitle="Cadastro"/>
          <Content >
            <Row>
              <UsuarioForm 
                descricao={this.state.descricao} 
                handleChange={this.handleChange}
                handleAdd={this.handleAdd}
                handleSearch={this.handleSearch}
                handleClear={this.handleClear}
                isUpdate={this.id ? false : true}
                /> 
              
              <UsuarioList 
                list={this.state.list}  
                handleRemove={this.handleRemove}
                handlePrepareUpdate={this.handlePrepareUpdate} 
                /> 
            </Row>
          </Content>
        </div>
      )
    }
}
  
