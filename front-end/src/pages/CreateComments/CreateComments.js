import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { Form, Container, Error, Success } from './stylesCreateComments';
import CommentsActions from '../../store/ducks/commentsDucks';

class CreateComments extends Component {
  state = {
    comment: '',
    hasError: null,
    location: false,
    id: '',
  };

  componentDidMount() {
    const { location } = this.props;
    if (location.state !== undefined) {
      const { id, comment } = location.state;
      this.setState({
        comment,
        location: true,
        id,
      });
    }
  }

  handleCreateUser = (e) => {
    e.preventDefault();
    const { saveRequestComments, updateRequestComments,  history } = this.props;
    const {id,  comment, location  } = this.state;
    this.setState({success: ''});
    
    if (!comment) {
      this.setState({ hasError: 'Preencha os campos' });
    } else {

      location
        ? updateRequestComments({
          id,
          comment,
          history,
        })
        : saveRequestComments({ comment, history });
    }
  };

  render() {
    const { comment, hasError } = this.state;
    const { fetching, error } = this.props.commentsReducer;
    let msgSuccess=''
    if(this.props.history.location.state === 'success'){
      msgSuccess = 'Parecer salvo com sucesso';
    }

    return (
      <Container>
        <Form onSubmit={this.handleCreateUser}>
          {((hasError || error ) && (<Error>{hasError || error}</Error> )) || 
          (msgSuccess && <Success>{msgSuccess}</Success>)}
          <input
            type="text"
            placeholder="Novo parecer"
            onChange={e => this.setState({ comment: e.target.value })}
            value={comment}
          />
          <button type="submit">
            {fetching ? <FontAwesomeIcon icon={faSpinner} spin /> : 'Salvar'}
          </button>
        </Form>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  commentsReducer: state.CommentsReducer,
});

const mapDispatchToProps = dispatch => bindActionCreators(CommentsActions, dispatch);

CreateComments.propTypes = {
  location: PropTypes.shape({
    comments: PropTypes.string,
   }),
  saveRequestComments: PropTypes.func.isRequired,
  updateRequestComments: PropTypes.func.isRequired,
  commentsReducer: PropTypes.shape({
    fetching: PropTypes.bool.isRequired,
    error: PropTypes.string,
  }),
  history: PropTypes.object,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(CreateComments);
