import {
  LIST_USER,
  LIST_USER_SUCCESS,
  LIST_USER_ERROR,
  SAVE_USER,
  SAVE_USER_SUCCESS,
  SAVE_USER_ERROR,
  USER_SHOW_EDIT_DIALOG,
  USER_HIDE_EDIT_DIALOG,
  USER_SHOW_DELETE_DIALOG,
  USER_HIDE_DELETE_DIALOG,
} from '../constants'

const initialState = {
  items: [],
  loading: false,
  error: null,
  idToDelete: null,
  deleting: false,
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
    case USER_SHOW_EDIT_DIALOG:
      return {
        ...state,
        openEditDialog: true,
        item: action.payload.user,
      }
    case USER_HIDE_EDIT_DIALOG:
      return {
        ...state,
        openEditDialog: false,
        item: null,
      }
    case USER_SHOW_DELETE_DIALOG:
      return {
        ...state,
        idToDelete: action.payload.id,
      }
    case USER_HIDE_DELETE_DIALOG:
      return {
        ...state,
        idToDelete: null,
      }
    default:
      return state;
  }
}
