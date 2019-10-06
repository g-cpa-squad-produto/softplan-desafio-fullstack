import React from "react";
import { Link } from "react-router-dom";

const CreateProcessoButton = () => {
  return (
    <React.Fragment>
      <Link to="/addProcesso" className="btn btn-dark btn-info">
        Novo Processo
      </Link>
    </React.Fragment>
  );
};

export default CreateProcessoButton;
