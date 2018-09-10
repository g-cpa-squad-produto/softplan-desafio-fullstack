import _ from 'lodash';
import axios from '../../../services/axios-instance';

export const FETCH_VINCULOS = 'FETCH_VINCULOS';
export const FETCH_VINCULOS_OK = 'FETCH_VINCULOS_OK';
export const FETCH_VINCULOS_FAIL = 'FETCH_VINCULOS_FAIL';

export const SAVE_VINCULOS = 'SAVE_VINCULOS';
export const SAVE_VINCULOS_OK = 'SAVE_VINCULOS_OK';
export const SAVE_VINCULOS_FAIL = 'SAVE_VINCULOS_FAIL';

export const fetchVinculosAct = () => ({type: FETCH_VINCULOS});
export const fetchVinculosOk = vinculaveis => ({type: FETCH_VINCULOS_OK, vinculaveis});
export const fetchVinculosFail = error => ({type: FETCH_VINCULOS_FAIL, error});

export const saveVinculosAct = () => ({type: SAVE_VINCULOS});
export const saveVinculosOk = () => ({type: SAVE_VINCULOS_OK});
export const saveVinculosFail = error => ({type: SAVE_VINCULOS_FAIL, error});

export const fetchVinculaveis = (id) => (dispatch) => {
    dispatch(fetchVinculosAct());
    return axios.get(`processos/${id}/vinculaveis`)
        .then(
            res => {
                dispatch(fetchVinculosOk(res.data));
            },
            err => dispatch(fetchVinculosFail(err))
        );
};

export const save = (users, process) => (dispatch) => {
    dispatch(saveVinculosAct());
    const vinculos = _.map(users, (user) => {
        return {
            user,
            processo: process
        };
    });
    return axios.post(`pareceres`, vinculos).then(
        () => {
            dispatch(saveVinculosOk())
        },
        err => dispatch(saveVinculosFail(err))
    );
};

const defaultState = {
    isFetching: false,
    vinculaveis: []
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case FETCH_VINCULOS:
            return {
                ...state,
                isFetching: true,
                error: null
            };
        case FETCH_VINCULOS_OK:
            return {
                ...state,
                isFetching: false,
                vinculaveis: action.vinculaveis
            };
        case FETCH_VINCULOS_FAIL:
            return {
                ...state,
                vinculaveis: [],
                error: action.error,
                isFetching: false
            };
        case SAVE_VINCULOS:
            return {
                ...state,
                isFetching: true,
                saveError: false
            };
        case SAVE_VINCULOS_OK:
            return {
                ...state,
                isFetching: true
            };
        case SAVE_VINCULOS_FAIL:
            return {
                ...state,
                isFetching: true,
                saveError: action.error
            };
        default:
            return state;
    }
};