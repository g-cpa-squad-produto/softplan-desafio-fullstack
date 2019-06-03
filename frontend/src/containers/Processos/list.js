import React from 'react';
import { Link } from 'react-router-dom';

import ProcessosList from '../../components/Processos/list';

const ProcessosListContainer = () => (
    <div className="card">
        <div className="card-body">
            <h4 className="card-title">Processos cadastrados</h4>
            <h6 className="card-subtitle mb-2 text-muted">Visão de triador</h6>
            <br />
            <Link to="/processos/new" className="btn btn-primary">Cadastrar processos</Link>
            <br /><br />
            <ProcessosList />
        </div>
    </div>
)

export default ProcessosListContainer;