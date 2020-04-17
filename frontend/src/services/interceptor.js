import axios from "axios";
import { toastr } from 'react-redux-toastr'

// Basepath da aplicação
axios.defaults.baseURL = 'http://localhost:8080';

// Interceptors para requests
axios.interceptors.request.use(
  config => {
    if(sessionStorage.session_data) {
      config.headers.Authorization = `Bearer ${JSON.parse(sessionStorage.session_data).token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

// Interceptors para responses
axios.interceptors.response.use(
  response => response,
  error => {
    let response = error.response;
    if(!response) {
      toastr.error('Ups!', 'Erro genérico')
    } else if(response.status === 401) {
      toastr.error('Ups!', 'Verifique suas credenciais e tente novamente')
    } else if(response.status === 403) {
      toastr.error('Falha!', 'Não autorizado')
    } else {
      toastr.error('Ups!', response.data.error)
    }
    return Promise.reject(error);
  },
);
