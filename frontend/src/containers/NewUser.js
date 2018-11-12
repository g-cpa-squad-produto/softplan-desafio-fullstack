import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { withStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import { saveUser } from '../actions/user'

import AppToolbar from './AppToolbar'
import Grid from '@material-ui/core/Grid'
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import Radio from '../components/Radio'

class NewUser extends Component {
  submit = (values) => {
    this.props.saveUser(values, this.props.history);
  }

  render() {
    const { handleSubmit, errorMessage } = this.props;

    return (
      <>
        <form onSubmit={handleSubmit(this.submit)}>
          <Grid container direction="column">
            <AppToolbar />
            <Grid container
              justify="center"
              alignItems="center"
              className="height-100vh width-100-pct">
              <Grid item>
                <Grid container direction="column" justify="center" alignItems="center">
                  <Typography className="mb-2" variant="h4">Novo Usuário</Typography>
                  <Grid container
                      direction="row"
                      justify="space-around"
                      alignItems="center"
                      className="mt-2 mb-2">
                    <Field name="name"
                        component={renderTextField}
                        label="Nome"
                        type="text" />
                    <Field name="email"
                        component={renderTextField}
                        label="E-mail"
                        type="email" />
                  </Grid>
                  <Grid container
                      direction="row"
                      justify="space-around"
                      alignItems="center"
                      className="mt-2 mb-2">
                    <Field name="password"
                        component={renderTextField}
                        label="Password"
                        type="password" />
                    <Field
                      name="role"
                      label="Perfil"
                      component={Radio}
                      options={{
                        ADMIN: 'Admin',
                        SCREENING: 'Triador',
                        FINISHER: 'Finalizador'
                      }}
                    />
                  </Grid>
                  <Grid container
                      direction="row"
                      justify="space-around"
                      alignItems="center" >
                    <Button type="submit" variant="contained" color="primary" className="mt-2 mb-2">
                      Salvar
                    </Button>
                  </Grid>
                  {errorMessage &&
                    <Grid item className="mt-2 mb-2">
                      <Grid container direction="column" justify="center" alignItems="center">
                        <Typography variant="body2">{errorMessage}</Typography>
                      </Grid>
                    </Grid>
                  }
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </form>
      </>
    )
  }
}

const renderTextField = ({
  input,
  label,
  type,
  meta: { touched, error },
  ...custom
}) => (
  <TextField variant="outlined"
    className="ml-2 mr-2"
    label={label}
    type={type}
    {...input}
    {...custom} />
)

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
NewUser = reduxForm({form: 'newUser'})(NewUser)

export default connect(mapStateToProps, {saveUser})(NewUser)
