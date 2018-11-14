import React from 'react'
import Dialog from '@material-ui/core/Dialog'
import DialogActions from '@material-ui/core/DialogActions'
import DialogTitle from '@material-ui/core/DialogTitle'
import DialogContentText from '@material-ui/core/DialogContentText'
import DialogContent from '@material-ui/core/DialogContent'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'

const defaultContent = "Deseja realmente excluir este item?"

const DeleteDialog = ({show, title, content = defaultContent, deleting, error, onCancel, onDelete}) =>
  !show ? null : (
    <Dialog open disableBackdropClick disableEscapeKeyDown>
      {title && <DialogTitle>{title}</DialogTitle>}
      <DialogContent>
        <DialogContentText>{content}</DialogContentText>
        {error && (
          <Typography variant="caption" color="error" className="mt-3">
            {error}
          </Typography>
        )}
      </DialogContent>
      <DialogActions>
      <Button color="secondary" disabled={deleting} onClick={onCancel}>
        Cancelar
      </Button>
      <Button component={Button} color="primary" loading={deleting ? deleting.toString() : undefined} onClick={() => onDelete()}>
        Excluir
      </Button>
    </DialogActions>
  </Dialog>
)

export default DeleteDialog
