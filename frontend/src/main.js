import React, {Component} from 'react'
import {Link} from 'react-router-dom'

import './style.css';

export default class Main extends Component {
    render() {
        return (
            <div className="main" >
                <Link to={`/usuarios`}>Usuarios</Link>                    
            </div>
        )
    }
}