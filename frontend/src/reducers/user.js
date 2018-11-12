import {
  LIST_USER,
  LIST_USER_SUCCESS,
  LIST_USER_ERROR,
  SAVE_USER,
  SAVE_USER_SUCCESS,
  SAVE_USER_ERROR,
} from '../constants'

const initialState = {
  items: [],
  loading: false,
  error: null
};

export default function userReducer(state = initialState, action) {
  switch(action.type) {
    case LIST_USER:
      return {
        ...state,
        loading: true,
        error: null
      }
    case LIST_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload.users
      }
    case LIST_USER_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        items: []
      }
    case SAVE_USER:
      return {
        ...state,
        loading: true,
        error: null
      }
    case SAVE_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload.user
      }
    case SAVE_USER_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        items: []
      }
    default:
      return state;
  }
}
