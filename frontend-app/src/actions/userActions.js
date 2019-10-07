import axios from "axios";
import jwt_decode from "jwt-decode";
import {
  GET_ERRORS,
  GET_USUARIOS,
  GET_PROCESSOS,
  GET_PROCESSOS_SEM_PARECER,
  DELETE_USUARIO,
  GET_USUARIO,
  GET_PARECER,
  SET_CURRENT_USER,
  GET_PARECER_USUARIO
} from "./types";
import setJWTToken from "../securtyUtils/setJWTToken";

export const createUsuario = (project, history) => async dispatch => {
  try {
    const res = await axios.post("http://localhost:8080/api/usuario", project, {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    });
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const updateUsuario = (id, project, history) => async dispatch => {
  try {
    const res = await axios.put(
      `http://localhost:8080/api/usuario/${id}`,
      project,
      {
        headers: { Authorization: localStorage.getItem("jwtToken") }
      }
    );
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const createProcesso = (project, history) => async dispatch => {
  try {
    const res = await axios.post(
      "http://localhost:8080/api/processo",
      project,
      {
        headers: { Authorization: localStorage.getItem("jwtToken") }
      }
    );
    history.push("/dashboardProcessos");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const createParecer = (
  usuarioId,
  processoId,
  project,
  history
) => async dispatch => {
  try {
    const res = await axios.post(
      `http://localhost:8080/api/parecer/${usuarioId}/${processoId}`,
      project,
      {
        headers: { Authorization: localStorage.getItem("jwtToken") }
      }
    );
    history.push("/dashboardProcessosPedentes");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getUsuarios = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/usuario/all", {
    headers: { Authorization: localStorage.getItem("jwtToken") }
  });
  dispatch({
    type: GET_USUARIOS,
    payload: res.data
  });
};

export const getUsuarioById = (usuarioId, history) => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/usuario/${usuarioId}`,
    {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    }
  );
  dispatch({
    type: GET_USUARIO,
    payload: res.data
  });
};

export const getProcessos = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/processo/all", {
    headers: { Authorization: localStorage.getItem("jwtToken") }
  });
  dispatch({
    type: GET_PROCESSOS,
    payload: res.data
  });
};
export const getProcessosSemParecer = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/processo/pendentes", {
    headers: { Authorization: localStorage.getItem("jwtToken") }
  });
  dispatch({
    type: GET_PROCESSOS_SEM_PARECER,
    payload: res.data
  });
};
export const getParecerByProcesso = processoId => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/parecer/${processoId}`,
    {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    }
  );
  dispatch({
    type: GET_PARECER,
    payload: res.data
  });
};

export const getPareceresByUsuario = usuarioId => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/parecer/usuario/${usuarioId}`,
    {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    }
  );
  dispatch({
    type: GET_PARECER_USUARIO,
    payload: res.data
  });
};

export const getParecerByUsuario = usuarioId => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/parecer/usuario/${usuarioId}`,
    {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    }
  );
  dispatch({
    type: GET_PARECER,
    payload: res.data
  });
};

export const deleteUsuarios = usuarioId => async dispatch => {
  if (window.confirm("Confirma a exclusão do usuário?")) {
    await axios.delete(`http://localhost:8080/api/usuario/${usuarioId}`, {
      headers: { Authorization: localStorage.getItem("jwtToken") }
    });
    dispatch({
      type: DELETE_USUARIO,
      payload: usuarioId
    });
  }
};

export const login = LoginRequest => async dispatch => {
  try {
    const res = await axios.post(
      "http://localhost:8080/api/auth",
      LoginRequest
    );
    const { token } = res.data;
    // const { user } = res.data.user;

    localStorage.setItem("jwtToken", token);
    localStorage.setItem("currentUser", res.data["user"]["tipo"]);
    localStorage.setItem("currentUserId", res.data["user"]["id"]);

    setJWTToken(token);
    const decoded = jwt_decode(token);
    dispatch({
      type: SET_CURRENT_USER,
      payload: { decoded }
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};
export const logout = () => dispatch => {
  localStorage.removeItem("jwtToken");
  localStorage.removeItem("currentUser");
  localStorage.removeItem("currentUserId");

  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: {}
  });
};
