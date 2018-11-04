const INITIAL_STATE = { list: [] };

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case "USUARIO_FETCHED":
      return { ...state, list: action.payload.data };
    case "FINALIZADORES_FETCHED":
      return { listFinalizadores: action.payload.data };

    default:
      return state;
  }
};
