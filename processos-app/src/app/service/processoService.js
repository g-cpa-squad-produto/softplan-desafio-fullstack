import ApiService from '../apiservice'

import ErroValidacao from '../exception/ErroValidacao'

export default class ProcessoService extends ApiService {

    constructor(){
        super('/api/processos')
    }

    obterListaMeses(){
        return  [
            { label: 'Selecione...', value: '' },
            { label: 'Janeiro', value: 1 },
            { label: 'Fevereiro', value: 2 },
            { label: 'Março', value: 3 },
            { label: 'Abril', value: 4 },
            { label: 'Maio', value: 5 },
            { label: 'Junho', value: 6 },
            { label: 'Julho', value: 7 },
            { label: 'Agosto', value: 8 },
            { label: 'Setembro', value: 9 },
            { label: 'Outubro', value: 10 },
            { label: 'Novembro', value: 11 },
            { label: 'Dezembro', value: 12 },
        ]
    }

    obterListaTipos(){
        return  [
            { label: 'Selecione...', value: '' },
            { label: 'Despesa' , value : 'DESPESA' },
            { label: 'Receita' , value : 'RECEITA' }
        ]

    }

    obterPorId(id){
        return this.get(`/${id}`);
    }

    alterarStatus(id, status){
        return this.put(`/${id}/atualiza-status`, { status })
    }

    validar(processo){
        const erros = [];

        if(!processo.ano){
            erros.push("Informe o Ano.")
        }

        if(!processo.mes){
            erros.push("Informe o Mês.")
        }

        if(!processo.parecer){
            erros.push("Informe a Descrição.")
        }

    
        if(erros && erros.length > 0){
            throw new ErroValidacao(erros);
        }
    }

    salvar(processo){
        return this.post('/', processo);
    }

    atualizar(processo){
        return this.put(`/${processo.id}`, processo);
    }

    consultar(processoFiltro){
        let params = `?ano=${processoFiltro.ano}`

        if(processoFiltro.mes){
            params = `${params}&mes=${processoFiltro.mes}`
        }

        if(processoFiltro.status){
            params = `${params}&status=${processoFiltro.status}`
        }

        if(processoFiltro.usuario){
            params = `${params}&usuario=${processoFiltro.usuario}`
        }

        if(processoFiltro.parecer){
            params = `${params}&parecer=${processoFiltro.parecer}`
        }

        return this.get(params);
    }

    deletar(id){
        return this.delete(`/${id}`)
    }
}