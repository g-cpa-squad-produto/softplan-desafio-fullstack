const ProcessosAtribuidosService = {
    findByUsuario
};

const urlBase = 'http://localhost:8085/processos/api';

function findByUsuario(idUsuario) {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };

    return fetch(`${urlBase}/processos-atribuidos?usuario=${idUsuario}`, requestOptions);
}

export default ProcessosAtribuidosService;