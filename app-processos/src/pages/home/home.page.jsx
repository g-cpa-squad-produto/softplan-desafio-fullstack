import React from 'react';
import { Link } from 'react-router-dom';

import { ApplicationContext } from '../../components/application-context';
import { UsuarioService } from '../../services/usuario.service';

class HomePage extends React.Component {
    
    constructor(props) {
        super(props);

        this.state = {
            user: {}
        };
    }

    componentDidMount() {
        this.setState({ 
            user: ApplicationContext.getUser()
        });
    }

    render() {
        const { user } = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">
                <h1>Hi {user.nome}!</h1>
                <p>You're logged in with React & Basic HTTP Authentication!!</p>
                <p>
                    <Link to="/login">Logout</Link>
                </p>
            </div>
        );
    }
}

export { HomePage };