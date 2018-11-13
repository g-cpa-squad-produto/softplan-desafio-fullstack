import React, { Component } from 'react'
import { Formik, Field, Form } from 'formik'
import * as yup from 'yup'
import { withStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import { saveUser, closeNewUser } from '../actions/user'

import AppToolbar from './AppToolbar'
import Grid from '@material-ui/core/Grid'
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import MenuItem from '@material-ui/core/MenuItem'

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
    const { errorMessage, classes } = this.props;

    return (
      <>
        <Formik
            initialValues={initialValues}
            validationSchema={yup.object().shape({
              role: yup.string().required(),
              password: yup.string().required(),
              name: yup.string().required(),
              email: yup
                .string()
                .required()
                .email(),
            })}
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
                            component={renderTextField}
                            label="Nome"
                            disabled={isSubmitting} />
                        <Field name="email"
                            component={renderTextField}
                            label="E-mail"
                            disabled={isSubmitting} />
                      </Grid>
                      <Grid container
                          direction="row"
                          justify="space-around"
                          alignItems="center"
                          className="">
                        <Field name="password"
                            component={renderTextField}
                            label="Password"
                            type="password"
                            disabled={isSubmitting} />
                        <Field
                            name="role"
                            component={renderTextField}
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

const renderTextField = ({ field, form: {touched, errors}, ...props }) => (
  <TextField
    className="ml-2 mr-2 mt-2 mb-2"
    variant="outlined"
    error={Boolean(touched[field.name] && errors[field.name])}
    helperText={touched[field.name] && errors[field.name]}
    {...field}
    {...props}
  />
)

const initialValues = {
  name: "",
  email: "",
  password: "",
  role: "ADMIN",
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
    errorMessage: state.user.error
  };
}

NewUser = withStyles(styles)(NewUser)
// NewUser = reduxForm({form: 'newUser'})(NewUser)

export default connect(mapStateToProps, {saveUser, closeNewUser})(NewUser)
