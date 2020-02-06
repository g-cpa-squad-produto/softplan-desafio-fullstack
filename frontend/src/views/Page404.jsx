import React from "react";
import { NavLink } from "react-router-dom";

export const Page404 = () => (
    <div>
        <div className="hero">
            <div className="hero-body">
                <div className="container">
                    <h1 className="title">Página não encontrada...</h1>
                    <h2 className="sub-title">
                        A página solicitada não existe ou está em construção.
                    </h2>
                    <br />
                    <NavLink to="/" className="button is-link">
                        Voltar para a página inicial
                    </NavLink>
                </div>
            </div>
        </div>
    </div>
);
