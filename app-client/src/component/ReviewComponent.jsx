import React, {Component} from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ReviewDataService from '../service/ReviewDataService';

class ReviewComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            review: '',
            user: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
        this.exitClicked = this.exitClicked.bind(this)
    }

    componentDidMount() {
        console.log('did mount review '+this.state.id + " - ReviewComponent")
        // console.log(this.state.id)

        // eslint-disable-next-line
        if (this.state.id == -1) {
            return
        }

        ReviewDataService.retrieveReviewById(this.state.id).then(
            response => this.setState(
                {
                    review: response.data.review,
                    user: response.data.user
                }
            )
        )
    }

    exitClicked(){
        console.log('exit clicked - ReviewComponent')
        this.props.history.push('/process')
        // window.history.back(-1)
    }

    onSubmit(values){
        console.log('submit review  - ReviewComponent')

        // let review = {
        //     id: this.state.id,
        //     review: values.review,
        //     user: values.user,
        //     
        // }

        // if(this.state.id === -1){
        //     ReviewDataService.createReview(review)
        //         .then(() => this.props.history.push('/process')) // TODO: navegar para o processo aberto
        // } else {
        //     ReviewDataService.updateReview(this.state.id, review)
        //         .then(() => this.props.history.push('/process')) // TODO: navegar para o processo aberto
        // }

        // // console.log(values);   
    }

    validate(values) {
        console.log('validate review - ReviewComponent')
        // let errors = {}
        // return errors
    }

    render() {
        let { id, review, user } = this.state

        return (
            <div className="container">
                <h2>Parecer</h2>
                <hr/>
                <div className="container">
                    <Formik 
                        initialValues={{ id, review, user }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="review" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="user" component="div" className="alert alert-warning" />
                                    {/* <fieldset className="form-group">
                                        <label>Id:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset> */}
                                    <fieldset className="form-group">
                                        <label>Parecerista:</label>
                                        <Field className="form-control" type="text" name="user" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Parecer:</label>
                                        <Field className="form-control" type="text" name="review" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Salvar</button>
                                    <button className="btn btn-default" onClick={this.exitClicked}>Voltar</button>
                                </Form>
                            )
                        }
                    </Formik>
                </div> 
            </div>
        )
    }
}

export default ReviewComponent