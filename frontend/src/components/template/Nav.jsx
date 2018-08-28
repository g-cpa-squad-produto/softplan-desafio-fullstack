import './Nav.css'
import React from 'react'
import { Link } from 'react-router-dom'


export default props => (
    <aside className="menu-area">
        <nav className="menu">
            <Link to="/">
                <i className="fa"></i> Início
            </Link>
            <Link to="/users">
                <i className="fa"></i> Usuários
            </Link>
            <Link to="/processes">
                <i className="fa"></i> Processos
            </Link>
        </nav>
    </aside>
)