import axios from '../../../services/axios-instance';

export const SAVING_USER = 'SAVING_USER';
export const SAVING_USER_OPENED = 'SAVING_USER_OPENED';
export const SAVING_USER_OK = 'SAVING_USER_OK';
export const SAVING_USER_ERROR = 'SAVING_USER_ERROR';

export const saveUserAct = () => ({type: SAVING_USER});
export const saveUserOpened = () => ({type: SAVING_USER_OPENED});
export const saveUserOk = () => ({type: SAVING_USER_OK});
export const saveUserError = (error) => ({type: SAVING_USER_ERROR, error});

export const saveUser = (user, callback) => (dispatch) => {
    dispatch(saveUserAct());
    let save = null;
    if (user.id) {
        save = axios.put(`/users/${user.id}`, user);
    } else {
        save = axios.post(`/users`, user);
    }
    return save.then(
        res => {
            dispatch(saveUserOk());
            callback();
        },
        err => dispatch(saveUserError(err))
    );
};

export const resetUser = () => (dispatch) => {
    dispatch(saveUserOpened());
};

const defaultSaveState = {
    loading: false
};

export default (state = defaultSaveState, action) => {
    switch (action.type) {
        case SAVING_USER:
            return {
                ...state,
                loading: true
            };
        case SAVING_USER_OK:
            return {
                ...state,
                loading: false
            };
        case SAVING_USER_ERROR:
            return {
                ...state,
                loading: false,
                error: action.error
            };
        case SAVING_USER_OPENED:
            return {
                ...state,
                loading: false,
                error: null
            };
        default:
            return state;
    }
};

