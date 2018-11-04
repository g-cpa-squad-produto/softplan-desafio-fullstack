import axios from "axios";
import { toastr } from "react-redux-toastr";
import { reset as resetForm, initialize } from "redux-form";
import { showTabs, selectTab } from "../common/tab/tabActions";

const SERVICE = "/processos";
const INITIAL_VALUES = {};

export function getList() {
  const request = axios.get(SERVICE);
  return {
    type: "PROCESSO_FETCHED",
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
    console.log(values);
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

export function showUpdate(processo) {
  return [
    showTabs("tabUpdate"),
    selectTab("tabUpdate"),
    initialize("processoForm", processo)
  ];
}

export function showDelete(processo) {
  return [
    showTabs("tabDelete"),
    selectTab("tabDelete"),
    initialize("processoForm", processo)
  ];
}

export function init() {
  return [
    showTabs("tabList", "tabCreate"),
    selectTab("tabList"),
    getList(),
    initialize("processoForm", INITIAL_VALUES)
  ];
}
