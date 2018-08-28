import { CREATE_PROCESS, EDIT_PROCESS, DELETE_PROCESS, SHOW_PROCESS, LIST_PROCESSES, LIST_PENDING_PROCESSES, UPDATE_FEEDBACK } from '../actions';

const defaultState = {
    list: [],
    pending: [],
    selected: {}
};

const process = (state = defaultState, action) => {
    switch (action.type) {
        case CREATE_PROCESS: {
            const { process } = action.payload;
            return { ...state, selected: process };
        }

        // case UPDATE_FEEDBACK: {
        //     const { id, process } = action.payload;
        //     const list = state.list.map((item) => {
        //         if (item.id === id) item = process;

        //         return item;
        //     });

        //     return { ...state, selected: process, list }
        // }

        case EDIT_PROCESS: {
            const { id, process } = action.payload;
            const list = state.list.map((item) => {
                if (item.id === id) item = process;

                return item;
            });

            return { ...state, selected: process, list }
        }

        case DELETE_PROCESS: {
            const { id } = action.payload;
            const list = state.list.filter((item) => {
                return item.id !== id;
            });

            return { ...state, selected: {}, list }
        }

        case SHOW_PROCESS: {
            const { process } = action.payload;
            return { ...state, selected: process }
        }

        case LIST_PROCESSES: {
            const { list } = action.payload;
            return { ...state, list }
        }

        case LIST_PENDING_PROCESSES: {
            const { list } = action.payload;
            return { ...state, pending: list }
        }

        default:
            return state;
    }
};

export default process;