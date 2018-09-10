import axios from '../../../services/axios-instance';

export const SAVING_PARECER = 'SAVING_PARECER';
export const SAVING_PARECER_OK = 'SAVING_PARECER_OK';
export const SAVING_PARECER_ERROR = 'SAVING_PARECER_ERROR';

export const saveParecerAct = () => ({type: SAVING_PARECER});
export const saveParecerOk = () => ({type: SAVING_PARECER_OK});
export const saveParecerError = (error) => ({type: SAVING_PARECER_ERROR, error});

export const save = (parecer, callback) => (dispatch) => {
    dispatch(saveParecerAct());
    return axios.put(`/pareceres/${parecer.id}`, parecer).then(
        () => {
            dispatch(saveParecerOk());
            callback();
        },
        err => dispatch(saveParecerError(err))
    );
};

const defaultSaveState = {
    loading: false
};

export default (state = defaultSaveState, action) => {
    switch (action.type) {
        case SAVING_PARECER:
            return {
                ...state,
                loading: true,
                error: null
            };
        case SAVING_PARECER_OK:
            return {
                ...state,
                loading: false
            };
        case SAVING_PARECER_ERROR:
            return {
                ...state,
                loading: false,
                error: action.error
            };
        default:
            return state;
    }
};