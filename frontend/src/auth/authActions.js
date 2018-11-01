import { toastr } from "react-redux-toastr";
import axios from "axios";

export function login(values) {
  return submit(values, "/login");
}

export function signup(values) {
  return submit(values, `/signup`);
}

function submit(values, url) {
  return dispatch => {
    const params = new URLSearchParams();
    params.append("login", values.login);
    params.append("senha", values.senha);
    axios
      .post(url, params)
      .then(resp => {
        dispatch([
          { type: "USER_FETCHED", payload: resp.headers.authorization }
        ]);
      })
      .catch(e => {
        toastr.error("Erro", e);
      });
  };
}

export function logout() {
  return { type: "TOKEN_VALIDATED", payload: false };
}

export function validateToken(token) {
  return dispatch => {
    if (token) {
      dispatch({ type: "TOKEN_VALIDATED", payload: true });
    } else {
      dispatch({ type: "TOKEN_VALIDATED", payload: false });
    }
  };
}
