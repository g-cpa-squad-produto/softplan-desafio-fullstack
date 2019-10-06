import React from "react";
import { Link } from "react-router-dom";

const CreateUserButton = () => {
  return (
    <React.Fragment>
      <Link to="/addUser" className="btn btn-dark btn-info">
        Novo Usuário
      </Link>
    </React.Fragment>
  );
};

export default CreateUserButton;
