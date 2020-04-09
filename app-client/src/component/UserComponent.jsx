import React, {Component} from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import UserDataService from "../service/UserDataService";

const USERNAME = 'username'

class UserComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            username: '',
            name: '',
            role: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
        this.exitClicked = this.exitClicked.bind(this)
    }

    componentDidMount() {
        // console.log(this.state.id)

        // eslint-disable-next-line
        if (this.state.id == -1) {
            return
        }

        UserDataService.retrieveUser(USERNAME, this.state.id).then(
            response => this.setState(
                {
                    username: response.data.username,
                    name: response.data.name,
                    role: response.data.role                
                }
            )
        )
    }

    exitClicked(){
        // console.log('exit clicked')
        this.props.history.push('/users')
    }

    onSubmit(values){
        let username = USERNAME

        let user = {
            id: this.state.id,
            username: values.username,
            name: values.name,
            role: values.role,
            targetDate: values.targetDate
        }

        if(this.state.id === -1){
            UserDataService.createUser(username, user)
                .then(() => this.props.history.push('/users'))
        } else {
            UserDataService.updateUser(username, this.state.id, user)
                .then(() => this.props.history.push('/users'))
        }

        // console.log(values);   
        // window.alert(values.id + "; " + values.name + "; " + values.role)     
    }

    validate(values) {
        let errors = {}

        if(!values.username){
            errors.username = 'Informe um nome de usuário'
        } 
        
        if(!values.name){
            errors.name = 'Informe um nome'
        } 

        if(!values.role){
            errors.role = 'Informe um privilégio'
        } 

        // console.log(errors)
        return errors
    }

    render() {
        let { id, username, name, role } = this.state

        return (
            <div>
                <h2>Detalhes do Usuário</h2>
                <hr/>
                <div className="container">
                    <Formik 
                        initialValues={{ id, username, name, role }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="username" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="name" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="role" component="div" className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Id:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset><fieldset className="form-group">
                                        <label>Nome de Usuário:</label>
                                        <Field className="form-control" type="text" name="username" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Nome:</label>
                                        <Field className="form-control" type="text" name="name" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Privilégio:</label>
                                        <Field className="form-control" as="select" name="role">
                                            <option value="">Selecione um privilégio</option>
                                            <option value="ADM">Administrador</option>
                                            <option value="TRIADOR">Triador</option>
                                            <option value="FINALIZADOR">Finalizador</option>
                                        </Field>
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

export default UserComponent