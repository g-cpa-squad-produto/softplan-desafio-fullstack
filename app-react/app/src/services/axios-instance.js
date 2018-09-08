import axios from 'axios';

let axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 10000,
});

export default axiosInstance;