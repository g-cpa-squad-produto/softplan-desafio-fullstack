import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";
import "./style.css";
import logoImg from "../../assets/process-logo.png";
import api from "../../services/api";
import validateRegister from "../../util/validateRegisterForm";
import { getToken } from "../../util/tokenUtils";
const INITAL_STATE = {
  name: "",
  username: "",
  email: "",
  password: "",
  role: [],
};

export default function Register() {
  const [name, setName] = useState(INITAL_STATE.name);
  const [username, setUsername] = useState(INITAL_STATE.username);
  const [email, setEmail] = useState(INITAL_STATE.email);
  const [password, setPassword] = useState(INITAL_STATE.password);
  const [role, setRole] = useState(INITAL_STATE.role);

  const [errors, setErrors] = useState({});
  const [serverResponse, setServerResponse] = useState("");
  const [isSubmitting, setSubmitting] = useState(false);
  const [rolesOptions, setRolesOptions] = useState([]);
  const perfilOptionLabel = "Selecione os perfis";

  useEffect(() => {
    api.get("auth/roles").then((response) => {
      const roles = response.data.map((res) => ({ value: res.role, label: res.profile, selected: false }));
      setRolesOptions(roles);
    });
  }, []);

  function handleMultipleSelect(e) {
    if (e.target.value === perfilOptionLabel) {
      return;
    }
    setRolesOptions(
      rolesOptions.map((opt) => (opt.value === e.target.value ? { ...opt, selected: !opt.selected } : opt))
    );
    let rol;
    if (role.includes(e.target.value)) {
      rol = role.filter((r) => r !== e.target.value);
    } else {
      rol = [...role, e.target.value];
    }
    //setRolesOptions([ ...rolesOptions]);
    setRole(rol);
  }

  function registerUser(e) {
    e.preventDefault();
    setServerResponse("");
    setSubmitting(true);
    const user = {
      name,
      email,
      username,
      password,
      role: role,
    };
    const formError = validateRegister(user);
    console.log(user);
    if (Object.keys(formError).length) {
      console.log("Tem erro", formError);
      setErrors(formError);
      setSubmitting(false);
      return;
    }
    setErrors({});
    api
      .post("/user/register", user, {
        headers: {
          Authorization: getToken(),
        },
      })
      .then((res) => {
        if (res.data.success) {
          setServerResponse(res.data.message);
          clearForm();
          setSubmitting(false);
        }
      })
      .catch((err) => {
        setServerResponse(err.response.data.message);
        setSubmitting(false);
      });
  }

  function clearForm() {
    setName(INITAL_STATE.name);
    setUsername(INITAL_STATE.username);
    setPassword(INITAL_STATE.password);
    setEmail(INITAL_STATE.email);
    setRole(INITAL_STATE.role);
    setErrors({});
  }

  return (
    <div className="register-container">
      <div className="content">
        <section>
          <img src={logoImg} width={300} alt="Process Manager" />
          <h1>Cadastro</h1>
          <p>Faça o seu cadastro no sistema de gerenciamento de processos.</p>
          <Link className="link" to="/process">
            <FiArrowLeft size={16} color="#E02041" />
            Voltar para home
          </Link>
        </section>

        <form onSubmit={registerUser} noValidate>
          <input
            placeholder="Nome do usuário"
            name="name"
            className={errors.name && "error-input"}
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          {errors.name && <p className="error-text">{errors.name}</p>}
          <input
            placeholder="Username"
            name="username"
            className={errors.username && "error-input"}
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          {errors.username && <p className="error-text">{errors.username}</p>}
          <input
            type="email"
            placeholder="E-mail"
            className={errors.email && "error-input"}
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          {errors.email && <p className="error-text">{errors.email}</p>}
          <div className="input-group">
            <div>
              <input
                type="password"
                name="password"
                placeholder="Senha"
                className={errors.password && "error-input"}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              {errors.password && <p className="error-text">{errors.password}</p>}
            </div>
            <div>
              <select name="role" onChange={handleMultipleSelect} className={errors.role && "error-input"}>
                <option>{perfilOptionLabel}</option>
                {rolesOptions.map((opt) => (
                  <option key={opt.value} value={opt.value}>
                    {`${opt.label} ${opt.selected ? "+ " : ""}`}
                  </option>
                ))}
              </select>
              {errors.role && <p className="error-text">{errors.role}</p>}
            </div>
          </div>
          {serverResponse && <p className="error-text">{serverResponse}</p>}
          <button disabled={isSubmitting} className="button" type="submit">
            Cadastrar
          </button>
        </form>
      </div>
    </div>
  );
}
