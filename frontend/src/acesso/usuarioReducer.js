const INITIAL_STATE = {
    nome: 'nome',
    login: 'JeanFlaragao',
    tipo: 'Administrador',
    list: [{ id: 1,
            descricao: 'teste 1',
            nome: 'teste 1'
        },{ id: 2,
            descricao: 'teste 2',
            nome: 'teste 3'
        },{ id: 3,
            descricao: 'teste 3',
            nome: 'teste 2'
        }]
}

export default (state = INITIAL_STATE, action) =>{
    switch(action.type){
        case 'NOME_CHANGED':
            return { ...state, nome: action.payload }
        case 'USUARIO_SEARCHED':
            return { ...state, list : action.payload.data }
        default:
            return state
    }
}