export default function validateProcess(values) {
  const errors = {};
  if (!values.title) {
    errors.title = "Título é obrigatório";
  }
  if (!values.substantiation) {
    errors.substantiation = "Descrição é obrigatório";
  }
  if (!values.status) {
    errors.status = "Statu é obrigatório";
  }
  if (!values.assignedUsersIds.length) {
    errors.assignedUsersIds = "Atribua pelo menos um usuário";
  }
  return errors;
}
