import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";
import "./style.css";
import logoImg from "../../assets/process-logo.png";
import api from "../../services/api";
import { getToken, getUser } from "../../util/tokenUtils";
import validateProcess from "../../util/validateProcessForm";

const INITAL_PROCES = {
  title: "",
  assignedUsersIds: [],
  substantiation: "",
  status: "NEW",
  opinions: "",
};
const userOptionLabel = "Selecione o(s) usuário(s)";

export default function NewProcess() {
  const [process, setProcess] = useState(INITAL_PROCES);
  const [userOptions, setUserOptions] = useState([]);
  const [isSubmitting, setSubmitting] = useState(false);
  const [serverResponse, setServerResponse] = useState("");
  const [errors, setErrors] = useState({});

  useEffect(() => {
    api
      .get("user/", {
        headers: {
          Authorization: getToken(),
        },
      })
      .then((response) => {
        const users = response.data.map((res) => ({ value: res.id, label: res.name, selected: false }));
        setUserOptions(users);
      });
  }, []);

  function createProcess(e) {
    e.preventDefault();
    setServerResponse("");
    setSubmitting(true);
    console.log(process);

    const formError = validateProcess(process);
    if (Object.keys(formError).length) {
      setErrors(formError);
      setSubmitting(false);
      return;
    } else {
      setErrors({});
    }
    let payload;
    if (!process.opinions) {
      payload = { ...process, opinions: [] };
    } else {
      payload = { ...process, opinions: [{ userId: getUser().id, opinion: process.opinions }] };
    }

    console.log(payload);
    api
      .post("/process", payload, {
        headers: {
          Authorization: getToken(),
        },
      })
      .then((res) => {
        if (res.data.success) {
          setServerResponse(res.data.message);
          clearForm();
          setSubmitting(false);
          setUserOptions(userOptions.map((opt) => ({ ...opt, selected: false })));
        }
      })
      .catch((err) => {
        setServerResponse(err.response.data.message);
        setSubmitting(false);
      });
  }

  function clearForm() {
    setProcess(INITAL_PROCES);
  }

  function handleMultipleSelect(e) {
    if (e.target.value === userOptionLabel) {
      console.log(process);
      return;
    }
    setUserOptions(
      userOptions.map((opt) => (opt.value.toString() === e.target.value ? { ...opt, selected: !opt.selected } : opt))
    );
    let assigUsersId;
    if (process.assignedUsersIds.includes(e.target.value)) {
      assigUsersId = process.assignedUsersIds.filter((r) => r !== e.target.value);
    } else {
      assigUsersId = [...process.assignedUsersIds, e.target.value];
    }
    setProcess({ ...process, assignedUsersIds: [...assigUsersId] });
  }

  return (
    <div className="new-process-container">
      <div className="content">
        <section>
          <img src={logoImg} width={300} alt="Process Manager" />
          <h1>Cadastre o novo processo</h1>
          <p>Descreva o processo detalhadamente e atribua os responsáveis .</p>
          <Link className="link" to="/process">
            <FiArrowLeft size={16} color="#E02041" />
            Voltar para home
          </Link>
        </section>

        <form onSubmit={createProcess}>
          <input
            placeholder="Título do processo"
            className={errors.title && "error-input"}
            value={process.title}
            onChange={(e) => setProcess({ ...process, title: e.target.value })}
          />
          {errors.title && <p className="error-text">{errors.title}</p>}
          <textarea
            placeholder="Descrição"
            className={errors.substantiation && "error-input"}
            value={process.substantiation}
            onChange={(e) => setProcess({ ...process, substantiation: e.target.value })}
          />
          {errors.substantiation && <p className="error-text">{errors.substantiation}</p>}
          <textarea
            placeholder="Opinião"
            className={errors.opinions && "error-input"}
            value={process.opinions}
            onChange={(e) => setProcess({ ...process, opinions: e.target.value })}
          />
          {errors.opinions && <p className="error-text">{errors.opinions}</p>}
          <select
            name="assignedUsersId"
            onChange={handleMultipleSelect}
            className={errors.assignedUsersIds && "error-input"}
          >
            <option>{userOptionLabel}</option>
            {userOptions.map((opt) => (
              <option key={opt.value} value={opt.value}>
                {`${opt.label} ${opt.selected ? "+ " : ""}`}
              </option>
            ))}
          </select>
          {errors.assignedUsersIds && <p className="error-text">{errors.assignedUsersIds}</p>}
          {serverResponse && <p className="error-text">{serverResponse}</p>}
          <button disabled={isSubmitting} className="button" type="submit">
            Cadastrar
          </button>
        </form>
      </div>
    </div>
  );
}
