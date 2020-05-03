import axios from 'axios'

const api = axios.create({ baseURL: 'http://localhost:9091'});

export default api;