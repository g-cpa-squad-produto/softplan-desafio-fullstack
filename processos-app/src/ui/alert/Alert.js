import React from 'react';

export const Alert = (props) => {
    return props.erros.map(erro =>{
        return(
            <div className="alert alert-danger" role="alert">
                {erro.mensagem}
                <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        )
    })
}