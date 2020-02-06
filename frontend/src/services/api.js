import axios from "axios";
import { toast } from "react-toastify";

import auth from "./auth";

/**
 * instância do axios com URL padrão
 */
const api = axios.create({
    baseURL: process.env.REACT_APP_API_URI
});

// interceptors para requisição
api.interceptors.request.use(
    async config => {
        
        const user = auth.getUser();

        // adiciona o token caso tenha usuário na sessão
        if (user) {
            const token = user.token;
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// interceptors para resposta
api.interceptors.response.use(
    response => response,
    async error => {
        // encerra sessão caso a resposta seja 401
        if (error.response && error.response.status === 401) {

            const user = auth.getUser();

            // verifica se existe sessão
            if (user) {
                toast.info("Sessão encerrada.");
            }
        }

        return Promise.reject(error);
    }
);

/**
 * Trata erros de requisição
 * @param {*} error
 * @param {{status: string}} customMessages
 */
const errorHandler = (error, customMessages = {}) => {
    const response = error.response;

    console.log(response);

    if (!response) {
        toast.error("Ops, ocorreu um erro inesperado");
        return;
    }

    const status = response.status;

    // verifica as mensagens do parâmetro
    let message = customMessages[status];

    // verifica a mensagem default
    message = message || errorMessages[status];

    // verifca a mesagem da requisição
    if (!message && response.data && response.data.message) {
        message = response.data.message;
    }

    if (message) toast.error(message);
};

const errorMessages = {
    409: "Registro já existente.",
    404: "Registro não encontrado.",
    400: "Dados inválidos, favor verificar o formulário.",
};

export { api, errorHandler };
