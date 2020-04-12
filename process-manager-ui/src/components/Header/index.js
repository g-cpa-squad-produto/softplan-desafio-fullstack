import React from "react";
import { FiPower } from "react-icons/fi";
import { getUser } from "../../util/tokenUtils";
import logout from "../../util/logout";
import { Link, useHistory } from "react-router-dom";

import "./style.css";
import logoImg from "../../assets/process-logo.png";

export default function Header() {
  const user = getUser();
  const userFirstName = user.name.split(" ")[0];
  const history = useHistory();

  function handleLogout() {
    logout();
    history.push("/");
  }
  return (
    <header>
      <img src={logoImg} alt="Process Manager" />
      <span>Bem vindo, {userFirstName}</span>
      <Link className="button" to="/process/new">
        Cadastrar novo processo
      </Link>
      <Link className="button" to="/register">
        Cadastrar novo usuário
      </Link>
      <button type="button" onClick={handleLogout}>
        <FiPower size={18} color="#E02041" />
      </button>
    </header>
  );
}
