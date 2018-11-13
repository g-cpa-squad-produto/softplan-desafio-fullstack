import React, { Component } from 'react'
import { connect } from 'react-redux'
import { showEditDialog } from '../actions/user'

import UserDialog from '../components/UserDialog'
import TableCell from '@material-ui/core/TableCell'
import TableRow from '@material-ui/core/TableRow'
import IconButton from '@material-ui/core/IconButton'
import DeleteIcon from '@material-ui/icons/Delete'
import EditIcon from '@material-ui/icons/Edit'

class UserItem extends Component {

  render() {

    const { user, showEditDialog } = this.props

    return (
      <>
        <TableRow key={user.id}>
          <TableCell numeric padding={'none'}>{user.id}</TableCell>
          <TableCell>{user.name}</TableCell>
          <TableCell>{user.email}</TableCell>
          <TableCell padding={'none'}><IconButton aria-label="Edit" onClick={() => showEditDialog(user)}><EditIcon /></IconButton></TableCell>
          <TableCell padding={'none'}><IconButton aria-label="Delete"><DeleteIcon /></IconButton></TableCell>
        </TableRow>
        <UserDialog id={user.id} />
      </>
    )
  }
}

UserItem = connect(null, {showEditDialog} )(UserItem)

export default UserItem
