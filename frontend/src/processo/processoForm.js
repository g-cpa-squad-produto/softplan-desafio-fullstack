import React from 'react'

import If from '../common/operators/If';
import InputHorizontal from '../common/form/InputHorizontal'
import ContentHeader from '../components/ContentHeader'
import Content from '../components/Content'
import Row from '../common/layout/Row'
import IconButton from '../common/layout/IconButton'

export default props => {
  const renderRows = () => {
    const list = props.aprovadores || []
    return list.map(usuario => (
        <tr key={usuario.id}>
          <td>{usuario.nome}</td>
          <td>{usuario.situacao}</td>
          <td>
            <IconButton styleButton='danger' icon='trash-o'
              onClick={() => props.handleRemoveAprovador(usuario)}>
            </IconButton>
          </td>
        </tr>
      ))
}
    return (
      <If test={props.edit}>
      <ContentHeader title="Processo" subTitle="Cadastro"/>
      <Content >
        <Row>      
          <div className="box box-info">
              <div role="form" className='form-horizontal'>
                <div className="box-body">

                  <InputHorizontal id="numero" label="Número:" type="text"
                                value={props.numero} onChange={props.setNumero}/>  

                  <InputHorizontal id="descricao" label="Descrição:" type="text"
                                value={props.descricao} onChange={props.setDescricao}/>  

                  <div className="form-group">
                      <label  className="col-sm-2 control-label">Situação:</label>
                      <div className="col-sm-10">
                        <select value={props.statusParecer} onChange={props.setStatusParecer} className="form-control">
                          <option value="">Selecione</option>
                          <option value="Pendente">Pendente</option>
                          <option value="Concluido">Concluido</option>
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

          <div className="box">
          <div className="box-header with-border">
            <h3 className="box-title">
              Aprovadores
            </h3>
          </div>
          <div className="box-body">
            <table className="table table-bordered">
              <tbody>
                <tr>
                  <th>Nome</th>
                  <th>Situação</th>
                  <th className='table-actions'>Ações</th>
                </tr>
                {renderRows()}
              </tbody>
            </table>
          </div>
        </div>
          </Row>
          </Content>
        </If> 
      
    )
}

