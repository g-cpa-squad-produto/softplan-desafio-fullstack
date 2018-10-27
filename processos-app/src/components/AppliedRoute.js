import React from "react";
import { Route } from "react-router-dom";

// Componente que cria o route onde o componente filho renderiza o conteudo passado pelo props
export default ({component: C, props: cProps, ...rest}) => 
    <Route {...rest} render={props => <C {...props} {...cProps} />} />;