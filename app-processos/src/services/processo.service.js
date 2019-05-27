import config from 'config';
import { authHeader } from '../helpers';

export const ProcessoService = {
    getAll
};

function getAll() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/processos`, requestOptions);
}