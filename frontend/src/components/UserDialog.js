import React, { Component } from 'react'
import { connect } from 'react-redux'
import { updateUser, hideEditDialog } from '../actions/user'
import { Formik, Field, Form } from 'formik'
import Dialog from '@material-ui/core/Dialog'
import DialogActions from '@material-ui/core/DialogActions'
import DialogContent from '@material-ui/core/DialogContent'
import DialogTitle from '@material-ui/core/DialogTitle'
import Button from '@material-ui/core/Button'
import Grid from '@material-ui/core/Grid'
import MenuItem from '@material-ui/core/MenuItem'
import Typography from '@material-ui/core/Typography'

import TextField from '../components/TextField'

const roles = [
  <MenuItem key={'ADMIN'} value={'ADMIN'}>
    <Typography variant="body1">Administrador</Typography>
  </MenuItem>,
  <MenuItem key={'SCREENING'} value={'SCREENING'}>
    <Typography variant="body1">Usuário-Triador</Typography>
  </MenuItem>,
  <MenuItem key={'FINISHER'} value={'FINISHER'}>
    <Typography variant="body1">Usuário-Finalizador</Typography>
  </MenuItem>,
]

class UserDialog extends Component {

  render () {
    const {open, item, hideEditDialog} = this.props

    if (!open) {
      return null
    }

    return (
      <>
      <Dialog open fullWidth disableBackdropClick disableEscapeKeyDown>
        <Formik
          initialValues={toFormData(item)}
          validate={validate}
          onSubmit={(values, actions) => {
            const { updateUser } = this.props;

            updateUser(values);
          }}
        >
          {({values, isSubmitting}) => (
            <Form noValidate>
              <DialogTitle>Editar Usuário</DialogTitle>
              <DialogContent>
                <Grid container direction="column" wrap="nowrap">
                  <Grid item>
                    <Grid container direction="row" justify="space-between" alignItems="center" spacing={16}>
                      <Grid item xs={12} md={6} className="mt-2">
                        <Field
                          name="name"
                          component={TextField}
                          label="Nome"
                          variant="outlined"
                          required
                          disabled={isSubmitting}
                          className="hidden"
                        />
                        <Field
                          name="name"
                          component={TextField}
                          label="Nome"
                          variant="outlined"
                          required
                          fullWidth
                          disabled={isSubmitting}
                        />
                      </Grid>
                      <Grid item xs={12} md={6} className="mt-2">
                        <Field
                          name="email"
                          component={TextField}
                          label="E-mail"
                          variant="outlined"
                          required
                          fullWidth
                          disabled={isSubmitting}
                        />
                      </Grid>
                      <Grid item xs={12} md={6} className="mt-2">
                        <Field
                            name="role"
                            component={TextField}
                            select
                            label="Perfil"
                            variant="outlined"
                            fullWidth
                            disabled={isSubmitting}>
                            {roles}
                        </Field>
                      </Grid>
                    </Grid>
                  </Grid>
                </Grid>
              </DialogContent>
              <DialogActions>
                <Button color="secondary" variant="contained" disabled={isSubmitting} onClick={() => hideEditDialog()}>
                  Cancelar
                </Button>
                <Button color="primary" variant="contained" loading={isSubmitting ? isSubmitting.toString() : undefined} type="submit">
                  Salvar
                </Button>
              </DialogActions>
            </Form>
          )}
        </Formik>
      </Dialog>
      </>
    )
  }
}

const toFormData = item => {
  return {
    id: item.id || "",
    name: item.name || "",
    email: item.email || "",
    role: item.role || "",
  }
}

const validate  = values => {
  const errors = {}

  if (!values.name) {
    errors.name = "Campo requerido"
  }

  if (!values.email) {
    errors.email = "Campo requerido"
  }

  if (!values.role) {
    errors.role = "Campo requerido"
  }

  return errors
}

const mapStateToProps = state => {
  return {
    open: state.user.openEditDialog,
    item: state.user.item,
  }
}

UserDialog = connect(mapStateToProps, {updateUser, hideEditDialog})(UserDialog)

export default UserDialog
