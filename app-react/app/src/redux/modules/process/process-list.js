import axios from '../../../services/axios-instance';

export const FETCH_PROCESS = 'FETCH_PROCESS';
export const FETCH_PROCESS_SUCCESS = 'FETCH_PROCESS_SUCCESS';
export const FETCH_PROCESS_ERROR = 'FETCH_PROCESS_ERROR';
export const SELECT_PROCESS = 'SELECT_PROCESS';

export const requestProcess = () => ({type: FETCH_PROCESS});
export const requestProcessOk = processes => ({type: FETCH_PROCESS_SUCCESS, processes});
export const requestProcessError = error => ({type: FETCH_PROCESS_ERROR, error});
export const selectProcessAct = process => ({type: SELECT_PROCESS, process});

const defaultState = {
    isFetching: false,
    processes: []
};

export const fetchProcesses = () => (dispatch) => {
    dispatch(requestProcess());
    return axios.get(`/processos`).then(
        res => {
            dispatch(requestProcessOk(res.data));
        },
        err => dispatch(requestProcessError(err))
    );
};

export const selectProcess = (process) => (dispatch) => {
    dispatch(selectProcessAct(process));
};

export default (state = defaultState, action) => {
    switch (action.type) {
        case FETCH_PROCESS:
            return {
                ...state,
                isFetching: true,
                error: null
            };
        case FETCH_PROCESS_SUCCESS:
            return {
                ...state,
                isFetching: false,
                processes: action.processes
            };
        case FETCH_PROCESS_ERROR:
            return {
                ...state,
                processes: [],
                error: action.error,
                isFetching: false
            };
        case SELECT_PROCESS:
            return {
                ...state,
                selected: action.process
            };
        default:
            return state;
    }
};