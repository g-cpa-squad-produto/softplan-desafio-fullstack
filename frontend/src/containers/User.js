import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withStyles } from '@material-ui/core/styles'
import { list } from '../actions/user'
import Grid from '@material-ui/core/Grid'
import Paper from '@material-ui/core/Paper'
import Typography from '@material-ui/core/Typography'
import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import TableFooter from '@material-ui/core/TableFooter'
import TablePagination from '@material-ui/core/TablePagination'
import Button from '@material-ui/core/Button'
import AddIcon from '@material-ui/icons/Add'

import AppToolbar from './AppToolbar'
import UserItem from '../components/UserItem'

class User extends Component {

  state = {
    page: 0,
    rowsPerPage: 10,
  }

  componentDidMount() {
    this.props.dispatch(list(this.state.page));
  }

  handleClickOpen = () => {
    this.props.history.push('/userNew');
  }

  handleChangePage = (event, page) => {
    this.props.dispatch(list(page));
    this.setState({ page });
  };

  render() {
    const { rowsPerPage, page } = this.state;
    const { loading, data, classes } = this.props;

    if (loading) {
      return (
        <>
          <Grid container direction="column">
            <AppToolbar />
            <Grid
              container
              justify="center"
              alignItems="center"
              className="height-fill-available width-100-pct m-0"
              >
              <Grid item>
                <Grid container direction="column" justify="center" alignItems="center">
                  <Typography variant="body2">Loading...</Typography>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </>
      );
    }

    let content
    if (!data) {
      content = "Error"
    } else {
      content = (
        <>
          <Typography className="mb-2" variant="h3">Usuários</Typography>
          <Paper className={classes.root}>
            <Table className={classes.table}>
              <TableHead>
                <TableRow>
                  <TableCell>Nome</TableCell>
                  <TableCell>E-mail</TableCell>
                  <TableCell>Perfil</TableCell>
                  <TableCell padding={'none'}></TableCell>
                  <TableCell padding={'none'}></TableCell>
                  <TableCell padding={'none'}></TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {data.users.map(user => (
                  <UserItem key={user.id} user={user} />
                ))}
              </TableBody>
              <TableFooter>
                <TableRow>
                  <TablePagination colSpan={5}
                    count={data.total}
                    rowsPerPage={rowsPerPage}
                    rowsPerPageOptions={rowsPerPage}
                    page={page}
                    labelRowsPerPage={'Registro por página'}
                    onChangePage={this.handleChangePage}/>
                </TableRow>
              </TableFooter>
            </Table>
          </Paper>
        </>
      )
    }

    return (
      <>
        <Grid container direction="column">
          <AppToolbar className={classes.toolbar} />
          <Grid
            container
            justify="center"
            alignItems="center"
            className="height-fill-available width-100-pct m-0 display-grid"
            >
            <Grid item className="mb-3">
              <Grid container direction="column" justify="center" alignItems="center">
                {content}
              </Grid>
            </Grid>
            <Button variant="fab"
              color="primary"
              aria-label="Add"
              className={classes.add}
              onClick={this.handleClickOpen}>
              <AddIcon />
            </Button>
          </Grid>
        </Grid>
      </>
    )
  }
}

const styles = theme => ({
  toolbar: {

  },
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: 700,
  },
  add: {
    position: 'fixed',
    bottom: 0,
    right: 0,
    marginRight: '30px',
    marginBottom: '30px',
    cursor: 'hover',
  },
});

const mapStateToProps = state => {
  return {
    data: state.user.data,
    loading: state.user.loading,
    error: state.user.error,
  }
}

User = withStyles(styles)(User)
User = connect(mapStateToProps)(User)

export default User
