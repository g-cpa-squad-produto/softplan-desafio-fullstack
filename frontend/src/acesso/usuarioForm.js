import React from 'react'

import InputHorizontal from '../common/form/InputHorizontal';
import If from '../common/operators/If'
import ContentHeader from '../components/ContentHeader'
import Content from '../components/Content'
import Row from '../common/layout/Row'
import IconButton from '../common/layout/IconButton'

export default props => {
    return (
      <If test={props.edit}>
      <ContentHeader title="Processo" subTitle="Cadastro"/>
      <Content >
        <Row>      
          <div className="box box-info">
              <div role="form" className='form-horizontal'>
                <div className="box-body">

                  <InputHorizontal id="nome" label="Nome:" type="text"
                                value={props.nome} onChange={props.setNome}/>  

                  <InputHorizontal id="login" label="Login:" type="text"
                                value={props.login} onChange={props.setLogin}/>  

                  <InputHorizontal id="senha" label="Senha:" type="password"
                                value={props.senha} onChange={props.setSenha}/> 

                  <div className="form-group">
                      <label  className="col-sm-2 control-label">Tipo:</label>
                      <div className="col-sm-10">
                        <select value={props.tipo} onChange={props.setTipo} className="form-control">
                          <option value="">Selecione</option>
                          <option value="Administrador">Administrador</option>
                          <option value="Triador">Triador</option>
                          <option value="Finalizador">Finalizador</option>
                        </select>
                      </div>
                  </div>
                            
                </div>                    
                <div className='box-footer'>
                  <div className='pull-right'>
                  <IconButton styleButton='primary' label='Salvar' onClick={props.handleProcessoSubmit}/>
                  <IconButton styleButton='default' label='Limpar' onClick={props.handleClear}/>
                  <IconButton styleButton='warning' label='Voltar' onClick={props.handleList}/>
                  </div>
                </div>
            </div>
          </div>
      </Row>
      </Content>
      </If>
    )
  }


