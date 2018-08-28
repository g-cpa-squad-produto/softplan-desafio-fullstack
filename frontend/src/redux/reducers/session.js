import { CREATE_SESSION, LOAD_SESSION, DELETE_SESSION } from '../actions';
import { persistSession, getPersistedSession, deletePersistedSession } from '../../libs/helpers';

const defaultState = {
    data: {}
};

const session = (state = defaultState, action) => {
    switch (action.type) {
        case CREATE_SESSION: {
            const { data } = action.payload;
            persistSession(data);
            return { ...state, data };
        }

        case LOAD_SESSION: {
            const data = getPersistedSession(); 
            return { ...state, data };
        }

        case DELETE_SESSION: {
            deletePersistedSession(); 
            return { ...state, data: {} };
        }

        default:
            return state;
    }
};

export default session;