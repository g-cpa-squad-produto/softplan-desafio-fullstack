import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import processoReducer from "./reducer";

export default combineReducers({
  errors: errorReducer,
  project: processoReducer
});
