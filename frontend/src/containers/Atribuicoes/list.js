import React from 'react';

import AtribuicoesList from '../../components/Atribuicoes/list';

const AtribuicoesListContainer = () => (
    <div className="card">
        <div className="card-body">
            <h4 className="card-title">Processos pendentes de parecer</h4>
            <h6 className="card-subtitle mb-2 text-muted">Visão de finalizador</h6>
            <br />
            <AtribuicoesList />
        </div>
    </div>
)

export default AtribuicoesListContainer;