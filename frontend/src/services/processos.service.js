import Config from '../commons/config';

const ProcessosService = {
    save,
    findById,
    findAll
};

const urlBase = `${Config.URL_API}/processos`;

function save(processo) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(processo)
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

function findAll() {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    return fetch(urlBase, requestOptions);
}

export default ProcessosService;