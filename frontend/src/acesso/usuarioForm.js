import React from 'react'
import Grid from '../template/Grid'
import IconButton from '../template/IconButton'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { changeDescricao } from './usuarioActions'

const UsuarioForm = props => (
        <div role='form' className='usuarioForm'>
          <Grid cols='12 9 10'>
              <input id='usuario' className='form-control'
                onChange={props.changeDescricao}
                placeholder='Adicone um Usuario'
                value={props.descricao}></input>
          </Grid>

          <Grid cols='12 3 2'>
            <IconButton style='primary' icon='plus' hide={!props.isUpdate}
              onClick={props.handleAdd}/>
            <IconButton style='success' icon='save' hide={props.isUpdate}
              onClick={props.handleAdd}/>
            <IconButton style='info' icon='search'
              onClick={props.handleSearch}/>
            <IconButton style='default' icon='close'
              onClick={props.handleClear}/>
          </Grid>
        </div>
      )

const mapStateToProps = state => ({descricao : state.usuario.descricao})
const mapDispatchToprops = dispatch => bindActionCreators({ changeDescricao}, dispatch)
export default connect(mapStateToProps, mapDispatchToprops )(UsuarioForm)     