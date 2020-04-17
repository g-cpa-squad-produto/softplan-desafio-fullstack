import Axios from 'axios';

export const getAllUsers = () => {
    return Axios.get('/api/user/', { params });
};

