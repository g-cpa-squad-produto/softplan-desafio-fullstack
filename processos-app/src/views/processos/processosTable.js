import React from 'react'


export default props => {

    const rows = props.processos.map( processo => {
        return (
            <tr key={processo.id}>
                <td>{processo.parecer}</td>
                <td>{processo.mes}</td>
                <td>{processo.status}</td>
                <td>
                    <button className="btn btn-success" title="Efetivar"
                            disabled={ processo.status !== 'PENDENTE' }
                            onClick={e => props.alterarStatus(processo, 'EFETIVADO')} 
                            type="button">
                            <i className="pi pi-check"></i>
                    </button>
                    <button className="btn btn-warning"  title="Cancelar"
                            disabled={ processo.status !== 'PENDENTE' }
                            onClick={e => props.alterarStatus(processo, 'CANCELADO')} 
                            type="button">
                            <i className="pi pi-times"></i>
                    </button>
                    <button type="button"   title="Editar"
                            className="btn btn-primary"
                            onClick={e => props.editAction(processo.id)}>
                            <i className="pi pi-pencil"></i>
                    </button>
                    <button type="button"  title="Excluir"
                            className="btn btn-danger" 
                            onClick={ e => props.deleteAction(processo)}>
                            <i className="pi pi-trash"></i>
                    </button>
                </td>
            </tr>
        )
    } )

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Descrição</th>
                    <th scope="col">Mês</th>
                    <th scope="col">Situação</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}

