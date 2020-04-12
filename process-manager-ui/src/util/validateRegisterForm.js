export default function validateRegister(values) {
  const errors = {};
  if (!values.name) {
    errors.name = "Nome é obrigatório";
  }
  if (!values.email) {
    errors.email = "Email é obrigatório";
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)) {
    errors.email = "Endereço de email inválido";
  }
  if (!values.username) {
    errors.username = "Nome do usuário é obrigatório";
  }
  if (!values.password) {
    errors.password = "Senha é obrigatório";
  }
  if (!values.role.length) {
    errors.role = "Perfil é obrigatório";
  }
  return errors;
}
