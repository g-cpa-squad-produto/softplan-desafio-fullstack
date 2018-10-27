const INITIAL_STATE = {
    descricao: 'Teste descrição',
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
        case 'DESCRICAO_CHANGED':
            return { ...state, descricao: action.payload }
        default:
            return state
    }
}