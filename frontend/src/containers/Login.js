import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { signIn } from '../actions/login'

import Grid from '@material-ui/core/Grid'
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'

class Login extends Component {

  submit = (values) => {
    this.props.signIn(values, this.props.history);
  }

  render() {
    const { errorMessage, loading, handleSubmit } = this.props;

    if (loading) {
      return (
        <>
          <Grid container direction="column">
            <Grid container
              justify="center"
              alignItems="center"
              className="height-fill-available width-100-pct">
              <Grid item>
                <Grid container direction="column" justify="center" alignItems="center">
                  <Typography variant="body2">Loading...</Typography>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </>
      )
    }

    return (
      <>
        <form onSubmit={handleSubmit(this.submit)}>
          <Grid container
            justify="center"
            alignItems="center"
            className="height-100vh width-100-pct">
            <Grid item>
              <Grid container direction="column" justify="center" alignItems="center">
                <Typography className="mb-2" variant="h4">Processes Management</Typography>
                <Field name="email"
                    component={renderTextField}
                    label="Email"
                    type="email" />
                <Field name="password"
                    component={renderTextField}
                    label="Password"
                    type="password" />
                <Button type="submit" variant="contained" color="primary" className="mt-2 mb-2">
                  Entrar
                </Button>
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
    className="mt-2 mb-2"
    label={label}
    type={type}
    {...input}
    {...custom} />
)

function mapStateToProps(state) {
  return {
    loading: state.login.loading,
    errorMessage: state.login.error
  };
}

Login = reduxForm({form: 'login'})(Login)

export default connect(mapStateToProps, {signIn})(Login)
