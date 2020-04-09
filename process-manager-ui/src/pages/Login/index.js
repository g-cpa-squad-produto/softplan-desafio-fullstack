import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { FiLogIn } from "react-icons/fi";

import api from "../../services/api";

import "./style.css";

import landingPageImg from "../../assets/process-landing-page.png";
import logoImg from "../../assets/process-logo.png";

export default function Login() {
  const [id, setId] = useState("");
  const history = useHistory();

  async function handleLogin(e) {
    e.preventDefault();

    try {
      const response = await api.post("sessions", { id });

      history.push("/profile");
    } catch (err) {
      alert("Falha no login, tente novamente");
    }
  }

  return (
    <div className="login-container">
      <section className="form">
        <img src={logoImg} width={400} alt="Be The Heroe" />
        <form onSubmit={handleLogin}>
          <h1>Faça seu login</h1>
          <input placeholder="Seu nome de usuário ou email" value={id} onChange={(e) => setId(e.target.value)} />
          <input placeholder="Sua senha" type="password" value={id} onChange={(e) => setId(e.target.value)} />
          <button className="button" type="submit">
            Entrar
          </button>
          <Link className="link" to="/register">
            <FiLogIn size={16} color="#E02041" />
            Não tenho cadastro
          </Link>
        </form>
      </section>
      <img src={landingPageImg} width={593} height={568} alt="Heroes" />
    </div>
  );
}
