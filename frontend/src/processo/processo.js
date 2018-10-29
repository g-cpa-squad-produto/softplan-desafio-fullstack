import React, { Component } from 'react'
import axios from 'axios'

import api from '../api'
import $ from 'jquery';
import PubSub from 'pubsub-js';
import TratadorErros from  '../TratadorErros';

import ProcessoForm from './processoForm'
import ProcessoList from './processoList' 

const URL = '/processos'

const config = {
  headers: {
    token: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUUklBRE9SIn0.hwjlTSgKPgeMGTX7C3R1wOs0Jry_zUwKehu4j-Q4hms',
  }
}

export default class Processo extends Component {

  constructor(props) {
      super(props);
      this.state = {id: '', numero: '', descricao: '', statusParecer:'', edit:false,
      aprovadores : [{
        id:1,
        nome:'Jean'
      }],

      list:[{
        id: 1,
        numero:'1',
        descricao:'desc1',
        statusParecer:'Pendente',
        aprovadores:[{
          id:1,
          nome:'Jean'
          },{
          id:2,
          nome:'João'}]
      },
      {
        id: 2,
        numero:'2',
        descricao:'desc2',
        statusParecer:'Pendente',
        aprovadores:[{
          id:1,
          nome:'Jean'
          }]
      }]};

      this.setNumero = this.setNumero.bind(this);
      this.setDescricao = this.setDescricao.bind(this);
      this.setStatusParecer = this.setStatusParecer.bind(this);   
      
      
      this.handleProcessoSubmit = this.handleProcessoSubmit.bind(this);
      this.handleClear = this.handleClear.bind(this);
      this.handleList = this.handleList.bind(this);

      this.handleRemove = this.handleRemove.bind(this);
      this.handlePrepareUpdate = this.handlePrepareUpdate.bind(this);
      this.handlePrepareInsert = this.handlePrepareInsert.bind(this);
      

  }

  componentDidMount() {
    axios.get(`${api.API_URL}usuarios`, config)
    .then(resp => this.console.log(resp))
    .catch(e => {
      console.log('Error: ', e.response)
    })
  }

  setNumero(e) {
    this.setState({...this.state, numero: e.target.value })
  }

  setDescricao(e) {
    this.setState({...this.state, descricao: e.target.value })
  }

  setStatusParecer(e) {
    this.setState({...this.state, statusParecer: e.target.value })
  }
  
  handleProcessoSubmit(e) {
    

    let data = {
      numero : this.state.numero.trim(),
      descricao : this.state.descricao.trim(),
      statusParecer : this.state.statusParecer
    }



    axios.post(`${api.API_URL}${URL}`, data, config)
    .then(resp => this.console.log(resp))
    .catch(e => {
      console.log('Error: ', e.response.data)
  })
  }

  handleProcessoSubmit1(e) {
    var titulo = this.state.titulo.trim();
    var preco = this.state.preco.trim();
    var autorId = this.state.autorId;

    $.ajax({
      url: 'http://localhost:8080/api/livros',
      contentType: 'application/json',
      dataType: 'json',
      type: 'POST',
      data: JSON.stringify({titulo:titulo,preco:preco,autorId:autorId}),
      success: function(novaListagem) {
          PubSub.publish( 'atualiza-lista-processos',novaListagem);            
          this.setState({titulo:'',preco:'',autorId:''});
      },
      error: function(resposta){
        if(resposta.status === 400){
          new TratadorErros().publicaErros(resposta.responseJSON);
        }
      },
      beforeSend: function(){
        PubSub.publish("limpa-erros",{});
      }            
    });  
    
    this.setState({id: '', numero: '', descricao: '', statusParecer:''});
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
    this.refresh()
  }

  refresh() {
    this.setState({...this.state, id: '', numero: '', descricao: '', statusParecer:''})
  }

  handlePrepareUpdate(e){
    this.setState({...this.state, id: e.id
                  , numero: e.numero
                  , descricao: e.descricao
                  , statusParecer: e.statusParecer
                  , edit : true
                  , aprovadores : e.aprovadores})
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
          
              
              <ProcessoForm
                numero={this.state.numero} 
                descricao={this.state.descricao} 
                statusParecer={this.state.statusParecer} 
                setDescricao = {this.setDescricao}
                setStatusParecer = {this.setStatusParecer}
                handleProcessoSubmit = {this.handleProcessoSubmit}
                handleClear={this.handleClear}
                handleList={this.handleList}
                edit={this.state.edit}
                aprovadores = {this.state.aprovadores}
                /> 
              
              <ProcessoList
                list = {this.state.list}
                edit={this.state.edit}
                handlePrepareUpdate={this.handlePrepareUpdate}
                handlePrepareInsert={this.handlePrepareInsert}
              /> 
        </div>
      )
    }
}
  
