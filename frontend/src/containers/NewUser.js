import React, { Component } from 'react'
import { Formik, Field, Form } from 'formik'
import { withStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import { saveUser, closeNewUser } from '../actions/user'

import AppToolbar from './AppToolbar'
import Grid from '@material-ui/core/Grid'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import MenuItem from '@material-ui/core/MenuItem'
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

class NewUser extends Component {

  render() {
    const { classes } = this.props;

    return (
      <>
        <Formik
            initialValues={initialValues}
            validate={validate}
            onSubmit={(values, actions) => {
              const { saveUser } = this.props

              saveUser(values, this.props.history);
            }}
          >
          {({values, isSubmitting}) => (
            <Form noValidate>
              <Grid container direction="column">
                <AppToolbar />
                <Grid container
                  justify="center"
                  alignItems="center"
                  className="height-100vh width-100-pct">
                  <Grid item>
                    <Grid container direction="column" justify="center" alignItems="center">
                      <Typography className="mb-2" variant="h3">Novo Usuário</Typography>
                      <Grid container
                          direction="row"
                          justify="space-around"
                          alignItems="center"
                          className="">
                        <Field name="name"
                            component={TextField}
                            label="Nome"
                            disabled={isSubmitting} />
                        <Field name="email"
                            component={TextField}
                            label="E-mail"
                            disabled={isSubmitting} />
                      </Grid>
                      <Grid container
                          direction="row"
                          justify="space-around"
                          alignItems="center"
                          className="">
                        <Field name="password"
                            component={TextField}
                            label="Password"
                            type="password"
                            disabled={isSubmitting} />
                        <Field
                            name="role"
                            component={TextField}
                            select
                            label="Perfil"
                            variant="outlined"
                            fullWidth
                            disabled={isSubmitting}
                            className={classes.formControl} >
                            {roles}
                          </Field>
                      </Grid>
                      <Grid container
                          direction="row"
                          justify="space-around"
                          alignItems="center" >
                        <Button color="secondary" variant="contained" disabled={isSubmitting} onClick={() => closeNewUser(this.props.history)}>
                          Cancelar
                        </Button>
                        <Button type="submit" variant="contained" color="primary" className="mt-2 mb-2">
                          Salvar
                        </Button>
                      </Grid>
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
            </Form>
          )}
        </Formik>
      </>
    )
  }
}

const initialValues = {
  name: "",
  email: "",
  password: "",
  role: "ADMIN",
}

const validate  = values => {
  const errors = {}

  if (!values.name) {
    errors.name = "Campo requerido"
  }

  if (!values.email) {
    errors.email = "Campo requerido"
  }

  if (!values.password) {
    errors.password = "Campo requerido"
  }

  if (!values.role) {
    errors.role = "Campo requerido"
  }

  return errors
}

const styles = theme => ({
  formControl: {
    margin: theme.spacing.unit,
    minWidth: 120,
    width: 223,
  },
});

function mapStateToProps(state) {
  return {
    loading: state.user.loading,
    data: state.user.data,
  };
}

NewUser = withStyles(styles)(NewUser)
// NewUser = reduxForm({form: 'newUser'})(NewUser)

export default connect(mapStateToProps, {saveUser, closeNewUser})(NewUser)
