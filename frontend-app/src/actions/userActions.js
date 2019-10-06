import axios from "axios";
import {
  GET_ERRORS,
  GET_USUARIOS,
  GET_PROCESSOS,
  GET_PROCESSOS_SEM_PARECER,
  DELETE_USUARIO,
  GET_USUARIO,
  GET_PARECER
} from "./types";

const token =
  "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cmlhZG9yQHNvZnRwbGFuLmNvbS5iciIsImNyZWF0ZWQiOjE1NzAzOTEzNzA2MzQsImV4cCI6MTU3MDk5NjE3MH0.AYq0qFbl5TzESirqhHeF13DmJWL12SlvcMw6Vtz8qsYUccXQB5PafrduVTvky78SG6zSCCV2VtHbE-UgMgSK-Q";
var config = {
  headers: { Authorization: token }
};

export const createUsuario = (project, history) => async dispatch => {
  try {
    const res = await axios.post(
      "http://localhost:8080/api/usuario",
      project,
      config
    );
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
      config
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
      config
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
      config
    );
    history.push("/dashboardProcessosPendentes");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getUsuarios = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/usuario/all", config);
  dispatch({
    type: GET_USUARIOS,
    payload: res.data
  });
};

export const getUsuarioById = (usuarioId, history) => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/usuario/${usuarioId}`,
    config
  );
  dispatch({
    type: GET_USUARIO,
    payload: res.data
  });
};

export const getProcessos = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/processo/all", config);
  dispatch({
    type: GET_PROCESSOS,
    payload: res.data
  });
};
export const getProcessosSemParecer = () => async dispatch => {
  const res = await axios.get(
    "http://localhost:8080/api/processo/pendentes",
    config
  );
  dispatch({
    type: GET_PROCESSOS_SEM_PARECER,
    payload: res.data
  });
};
export const getParecerByProcesso = processoId => async dispatch => {
  const res = await axios.get(
    `http://localhost:8080/api/parecer/${processoId}`,
    config
  );
  dispatch({
    type: GET_PARECER,
    payload: res.data
  });
};

export const deleteUsuarios = usuarioId => async dispatch => {
  if (window.confirm("Confirma a exclusão do usuário?")) {
    await axios.delete(
      `http://localhost:8080/api/usuario/${usuarioId}`,
      config
    );
    dispatch({
      type: DELETE_USUARIO,
      payload: usuarioId
    });
  }
};
