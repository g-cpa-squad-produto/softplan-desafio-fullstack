import React, { useState, useEffect } from "react";
import { FiUserPlus } from "react-icons/fi";

import "./style.css";
import Header from "../../components/Header";
import api from "../../services/api";
import { getToken } from "../../util/tokenUtils";

export default function ProcessList() {
  const [response, setResponse] = useState({});

  useEffect(() => {
    api
      .get("process", {
        headers: {
          Authorization: getToken(),
        },
      })
      .then((res) => setResponse(res.data))
      .catch((err) => console.log(err));
  }, []);

  console.log(response["content"]);

  return (
    <div className="process-container">
      <Header />
      <h1>Processos cadastrados</h1>
      <ul>
        {response["content"] &&
          response.content.map((proc) => (
            <li key={proc.id}>
              <strong>Process:</strong>
              <p>{proc.title}</p>
              <strong>Descrição:</strong>
              <p>{proc.substantiation}</p>
              <strong>Responsáveis:</strong>
              {proc.assignedUsers.map((user, indice) => (
                <p style={{ position: "relative", float: "left", marginLeft: "10px" }}>
                  <strong style={{ position: "relative", float: "left" }}>{indice + 1}.</strong>
                  {" " + user.name.split(" ")[0]}
                </p>
              ))}
              <button type="button">
                <FiUserPlus size={20} color="#a8a8b3" />
              </button>
            </li>
          ))}
      </ul>
    </div>
  );
}
