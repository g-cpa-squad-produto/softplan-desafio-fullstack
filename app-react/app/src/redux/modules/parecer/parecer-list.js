import axios from '../../../services/axios-instance';

export const FETCH_PARECER = 'FETCH_PARECER';
export const FETCH_PARECER_SUCCESS = 'FETCH_PARECER_SUCCESS';
export const FETCH_PARECER_ERROR = 'FETCH_PARECER_ERROR';
export const SELECT_PARECER = 'SELECT_PARECER';

export const requestParecer = () => ({type: FETCH_PARECER});
export const requestParecerOk = processes => ({type: FETCH_PARECER_SUCCESS, processes});
export const requestParecerError = error => ({type: FETCH_PARECER_ERROR, error});
export const selectParecerAct = process => ({type: SELECT_PARECER, process});

const defaultState = {
    isFetching: false,
    pareceres: []
};

export const fetchPareceres = (userId) => (dispatch) => {
    dispatch(requestParecer());
    return axios.get(`/pareceres`, {params: {user: userId}}).then(
        res => {
            dispatch(requestParecerOk(res.data));
        },
        err => dispatch(requestParecerError(err))
    );
};

export const selectParecer = (process) => (dispatch) => {
    dispatch(selectParecerAct(process));
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case FETCH_PARECER:
            return {
                ...state,
                isFetching: true,
                error: null
            };
        case FETCH_PARECER_SUCCESS:
            return {
                ...state,
                isFetching: false,
                pareceres: action.processes
            };
        case FETCH_PARECER_ERROR:
            return {
                ...state,
                pareceres: [],
                error: action.error,
                isFetching: false
            };
        case SELECT_PARECER:
            return {
                ...state,
                selected: action.process
            };
        default:
            return state;
    }
};