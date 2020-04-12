import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { FiLogIn } from "react-icons/fi";
import { ACCESS_TOKEN, TOKEN_TYPE } from "../../constants";

import api from "../../services/api";

import "./style.css";

import ladingPageImg from "../../assets/process-landing-page.png";
import logoImg from "../../assets/process-logo.png";

export default function Logon() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  async function handleLogon(e) {
    e.preventDefault();

    try {
      const response = await api.post("auth/signin", { usernameOrEmail: id, password });

      localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
      localStorage.setItem(TOKEN_TYPE, response.data.tokenType);

      history.push("/process");
    } catch (err) {
      alert("Falha no login, tente novamente");
    }
  }

  return (
    <div className="logon-container">
      <div className="content">
        <section className="form">
          <img src={logoImg} width={300} alt="Process Manager" />
          <form onSubmit={handleLogon}>
            <h1>Faça seu logon</h1>
            <input placeholder="Seu nome de usuário ou email" value={id} onChange={(e) => setId(e.target.value)} />
            <input
              type="password"
              placeholder="Sua senha"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button className="button" type="submit">
              Entrar
            </button>
            <Link className="link" to="/register">
              <FiLogIn size={16} color="#E02041" />
              Não tenho cadastro
            </Link>
          </form>
        </section>
        <img src={ladingPageImg} width={550} alt="Heroes" />
      </div>
    </div>
  );
}
