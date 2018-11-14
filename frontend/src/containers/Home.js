import React, { Component } from 'react'

import AppToolbar from './AppToolbar'
import Grid from '@material-ui/core/Grid'
import Typography from '@material-ui/core/Typography'

class Home extends Component {

  render() {
    return (
      <>
        <Grid container direction="column">
          <AppToolbar />
          <Grid
            container
            justify="center"
            alignItems="center"
            className="height-fill-available width-100-pct m-0 discount-toolbar"
            >
            <Grid item>
              <Typography variant="h4">Processess Management</Typography>
            </Grid>
          </Grid>
        </Grid>
      </>
    )
  }
}

export default Home
