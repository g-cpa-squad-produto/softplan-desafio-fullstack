import { CREATE_ACCOUNT, EDIT_ACCOUNT, DELETE_ACCOUNT, SHOW_ACCOUNT, LIST_ACCOUNTS } from '../actions';

const defaultState = {
    list: [],
    selected: {}
};

const account = (state = defaultState, action) => { console.log('oi',action)
    switch (action.type) {
        case CREATE_ACCOUNT: {
            const { account } = action.payload;
            return { ...state, selected: account };
        }

        case EDIT_ACCOUNT: {
            const { id, account } = action.payload;
            const list = state.list.map((item) => {
                if (item.id === id) item = account;

                return item;
            });

            return { ...state, selected: account, list }
        }

        case DELETE_ACCOUNT: {
            const { id } = action.payload;
            const list = state.list.filter((item) => {
                return item.id !== id;
            });

            return { ...state, selected: {}, list }
        }

        case SHOW_ACCOUNT: {
            const { account } = action.payload;
            return { ...state, selected: account }
        }

        case LIST_ACCOUNTS: {
            const { list } = action.payload;
            return { ...state, list }
        }

        default:
            return state;
    }
};

export default account;