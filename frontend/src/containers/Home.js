import React, { Component } from 'react'

import Grid from '@material-ui/core/Grid'

class Home extends Component {

  render() {
    return (
      <>
        <Grid container direction="column">
          <Grid item xs>
            <h1>Home Screen</h1>
          </Grid>
        </Grid>
      </>
    )
  }
}

export default Home
