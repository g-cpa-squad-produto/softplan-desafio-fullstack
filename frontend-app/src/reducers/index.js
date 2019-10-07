import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import processoReducer from "./reducer";
import securityReducer from "./securityReducer";

export default combineReducers({
  errors: errorReducer,
  project: processoReducer,
  security: securityReducer
});
