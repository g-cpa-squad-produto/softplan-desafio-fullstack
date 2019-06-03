import Config from '../commons/config';

const AtribuicoesService = {
    save,
    findById
};

const urlBase = `${Config.URL_API}/atribuicoes`;

function save(atribuicao) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(atribuicao)
    };
    return fetch(urlBase, requestOptions);
}

function findById(id) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    return fetch(`${urlBase}/${id}`, requestOptions);
}

export default AtribuicoesService;