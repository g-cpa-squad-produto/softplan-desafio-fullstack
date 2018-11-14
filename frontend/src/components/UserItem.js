import React, { Component } from 'react'
import { connect } from 'react-redux'
import { showEditDialog, showDeleteDialog, hideDeleteDialog, del } from '../actions/user'
import TableCell from '@material-ui/core/TableCell'
import TableRow from '@material-ui/core/TableRow'
import IconButton from '@material-ui/core/IconButton'
import DeleteIcon from '@material-ui/icons/Delete'
import EditIcon from '@material-ui/icons/Edit'

import UserDialog from '../components/UserDialog'
import DeleteDialog from '../components/DeleteDialog'

class UserItem extends Component {

  render() {
    const { user, showEditDialog, showDeleteDialog, hideDeleteDialog, del, idToDelete, deleting } = this.props

    return (
      <>
        <TableRow key={user.id}>
          <TableCell numeric padding={'none'}>{user.id}</TableCell>
          <TableCell>{user.name}</TableCell>
          <TableCell>{user.email}</TableCell>
          <TableCell padding={'none'}><IconButton aria-label="Edit" onClick={() => showEditDialog(user)}><EditIcon /></IconButton></TableCell>
          <TableCell padding={'none'}><IconButton aria-label="Delete" onClick={() => showDeleteDialog(user.id)}><DeleteIcon /></IconButton></TableCell>
        </TableRow>
        <UserDialog id={user.id} />
        <DeleteDialog
          show={user.id === idToDelete}
          content={`Deseja realmente excluir o usuário "${user.name}" ?`}
          deleting={deleting}
          onCancel={() => hideDeleteDialog()}
          onDelete={() => del(idToDelete)}
        />
      </>
    )
  }
}

const mapStateToProps = state => {
  return {
    idToDelete: state.user.idToDelete,
    deleting: state.user.deleting,
  }
}

UserItem = connect(mapStateToProps, {showEditDialog, showDeleteDialog, hideDeleteDialog, del} )(UserItem)

export default UserItem
