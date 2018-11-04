import axios from "axios";
import { toastr } from "react-redux-toastr";
import { reset as resetForm, initialize } from "redux-form";
import { showTabs, selectTab } from "../common/tab/tabActions";

const SERVICE = "/usuarios";
const INITIAL_VALUES = {};

export function getList() {
  const request = axios.get(SERVICE);
  return {
    type: "USUARIO_FETCHED",
    payload: request
  };
}

export function getFinalizadores() {
  const request = axios.get(`${SERVICE}/finalizadores/`);
  return {
    type: "FINALIZADORES_FETCHED",
    payload: request
  };
}

export function create(values) {
  return submit(values, "post");
}

export function update(values) {
  return submit(values, "put");
}

export function remove(values) {
  return submit(values, "delete");
}

function submit(values, method) {
  return dispatch => {
    const id = values.id ? values.id : "";
    console.log(id);
    axios[method](`${SERVICE}/${id}`, values)
      .then(resp => {
        toastr.success("Sucesso", "Operação Realizada com sucesso.");
        dispatch(init());
      })
      .catch(e => {
        debugger;
        e.response.data.errors.forEach(error =>
          toastr.error(
            "Erro",
            `${error.field} ${error.objectName} ${error.defaultMessage} `
          )
        );
      });
  };
}

export function showUpdate(usuario) {
  return [
    showTabs("tabUpdate"),
    selectTab("tabUpdate"),
    initialize("usuarioForm", usuario)
  ];
}

export function showDelete(usuario) {
  return [
    showTabs("tabDelete"),
    selectTab("tabDelete"),
    initialize("usuarioForm", usuario)
  ];
}

export function init() {
  return [
    showTabs("tabList", "tabCreate"),
    selectTab("tabList"),
    getList(),
    initialize("usuarioForm", INITIAL_VALUES)
  ];
}
