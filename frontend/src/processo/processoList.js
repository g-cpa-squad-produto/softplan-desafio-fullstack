import React from 'react'


import If from '../common/operators/If';
import ContentHeader from '../components/ContentHeader'
import Content from '../components/Content'
import Row from '../common/layout/Row'
import IconButton from '../common/layout/IconButton'


export default props => {
    
  const renderRows = () => {
      const list = props.list || []
      return list.map(processo => (
          <tr key={processo.id}>
            <td>{processo.numero}</td>
            <td>{processo.descricao}</td>
            <td>{processo.statusParecer}</td>
            <td>
              <IconButton styleButton='edit' icon='edit'
                onClick={() => props.handlePrepareUpdate(processo)}>
              </IconButton>
              <IconButton styleButton='danger' icon='trash-o'
                onClick={() => props.handleRemove(processo)}>
              </IconButton>
            </td>
          </tr>
        ))
  }

  return (
    <If test={!props.edit}>
    <ContentHeader title="Processo" subTitle="Lista"/>
    <Content >
      <Row>
        <div className="box">
          <div className="box-header with-border">
            <h3 className="box-title">
                <IconButton styleButton='primary' label='Incluir' onClick={props.handlePrepareInsert}/>
            </h3>
          </div>
          <div className="box-body">
            <table className="table table-bordered">
              <tbody>
                <tr>
                  <th className='table-codigo'>Número</th>
                  <th>Descrição</th>
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