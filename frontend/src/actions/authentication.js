import axios from 'axios';
import { GET_ERRORS, SET_CURRENT_USER } from './types';
import setAuthToken from '../setAuthToken';
import jwt_decode from 'jwt-decode';
import { config } from './config';

export const loginUser = (user) => dispatch => {
    axios.post('http://localhost:8080/login', user, config)
        .then(res => {
            const { token, type } = res.data;
            localStorage.setItem('role', type);
            localStorage.setItem('jwtToken', token);
            setAuthToken(token);
            const decoded = jwt_decode(token);
            dispatch(setCurrentUser(decoded));
        })
        .catch(err => {
            dispatch({
                type: GET_ERRORS,
                payload: err.response
            });
        });
}

export const setCurrentUser = decoded => {
    return {
        type: SET_CURRENT_USER,
        payload: decoded
    }
}

export const logoutUser = (history) => dispatch => {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('role');
    setAuthToken(false);
    dispatch(setCurrentUser({}));
    history.push('/login');
}