import React from "react";

import MenuItem from "./MenuItem";

export default props => (
  <ul className="sidebar-menu" data-widget="tree">
    <MenuItem path="/" label="Home" icon="th" />

    <MenuItem path="/usuario" label="Usuarios" />

    <MenuItem path="/processo" label="Processo" />

    <MenuItem path="/parecer" label="Parecer" />
  </ul>
);
