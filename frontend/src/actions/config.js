export const config = {
    headers: {
        'Access-Control-Allow-Origin': '*',
    },
    proxy: {
        host: 'localhost',
        port: 8080
    }
};

export const API_URL = 'http://localhost:8080/api/';