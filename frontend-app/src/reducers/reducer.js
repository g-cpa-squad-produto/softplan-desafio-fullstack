import {
  GET_USUARIOS,
  GET_PROCESSOS,
  GET_PROCESSOS_SEM_PARECER,
  DELETE_USUARIO,
  GET_USUARIO,
  GET_PARECER
} from "../actions/types";

const initialState = {
  projects: [],
  project: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_USUARIOS:
      return {
        ...state,
        projects: action.payload
      };
    case GET_USUARIO:
      return {
        ...state,
        project: action.payload
      };
    case GET_PROCESSOS:
      return {
        ...state,
        projects: action.payload
      };
    case GET_PARECER:
      return {
        ...state,
        project: action.payload
      };
    case GET_PROCESSOS_SEM_PARECER:
      return {
        ...state,
        projects: action.payload
      };
    case DELETE_USUARIO:
      return {
        ...state,
        projects: state.projects.filter(
          project => project.id !== action.payload
        )
      };
    default:
      return state;
  }
}
