import React from 'react'

import './style.css'

const Header = () => (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
        <a className="navbar-brand" href="\">Desafio</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
                <li className="nav-item active">
                    <a className="nav-link" href="/usuarios">Usuarios</a> 
                </li>
                <li className="nav-item active">
                    <a className="nav-link" href="/processos">Processos</a>
                </li>
                <li className="nav-item active">
                    <a className="nav-link" href="/pareceres">Pareceres</a>
                </li>
            </ul>
        </div>
    </nav>
);

export default Header;