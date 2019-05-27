import config from 'config';
import { authHeader } from '../helpers';

export const ProcessoAtribuicaoService = {
    getByUsuario
};

function getByUsuario(idUsuario) {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/processos-atribuidos?usuario=${idUsuario}`, requestOptions);
}