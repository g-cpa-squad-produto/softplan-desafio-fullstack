import axios from '../../../services/axios-instance';

export const SAVING_PROCESS = 'SAVING_PROCESS';
export const SAVING_PROCESS_OK = 'SAVING_PROCESS_OK';
export const SAVING_PROCESS_ERROR = 'SAVING_PROCESS_ERROR';

export const saveProcessAct = () => ({type: SAVING_PROCESS});
export const saveProcessOk = () => ({type: SAVING_PROCESS_OK});
export const saveProcessError = (error) => ({type: SAVING_PROCESS_ERROR, error});

export const save = (process, callback) => (dispatch) => {
    dispatch(saveProcessAct());
    return axios.post(`/processos`, process).then(
        () => {
            dispatch(saveProcessOk());
            callback();
        },
        err => dispatch(saveProcessError(err))
    );
};

const defaultSaveState = {
    loading: false
};

export default (state = defaultSaveState, action) => {
    switch (action.type) {
        case SAVING_PROCESS:
            return {
                ...state,
                loading: true,
                error: null
            };
        case SAVING_PROCESS_OK:
            return {
                ...state,
                loading: false
            };
        case SAVING_PROCESS_ERROR:
            return {
                ...state,
                loading: false,
                error: action.error
            };
        default:
            return state;
    }
};
