import { combineReducers } from "redux";
import { reducer as formReducer } from "redux-form";
import { reducer as toastrReducer } from "react-redux-toastr";

import TabReducer from "../common/tab/tabReducer";
import AuthReducer from "../auth/authReducer";
import UsuarioReducer from "../usuario/usuarioReducer";

const rootReducer = combineReducers({
  tab: TabReducer,
  toastr: toastrReducer,
  usuario: UsuarioReducer,
  auth: AuthReducer,
  form: formReducer
});

export default rootReducer;
