import React from 'react'
import IconButton from '../template/IconButton'
import { connect } from 'react-redux'

const UsuarioList = props => {
    
  const renderRows = () => {
      const list = props.list || []
      return list.map(usuario => (
          <tr key={usuario.id}>
            <td>{usuario.nome}</td>
            <td>
              <IconButton style='edit' icon='edit'
                onClick={() => props.handlePrepareUpdate(usuario)}>
              </IconButton>
              <IconButton style='danger' icon='trash-o'
                onClick={() => props.handleRemove(usuario)}>
              </IconButton>
            </td>
          </tr>
        ))
  }
    
    

  return (
    
    <div className="box-body">
      <table className='table table-bordered'>
        <thead>
            <tr>
              <th>Descrição</th>
              <th className='table-actions'>Ações</th>
            </tr>
        </thead>
        <tbody>
            {renderRows()}
        </tbody>
      </table>
    </div>

    )
  }

const mapStateToProps = state =>({ list: state.usuario.list})

export default connect(mapStateToProps)(UsuarioList)