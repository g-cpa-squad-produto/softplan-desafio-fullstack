import UsuariosService from '../services/usuarios.service';

export const canRoute = (props) => {
    const loggedUser = UsuariosService.getLoggedUser();
    return (loggedUser !== null) && loggedUser.perfil === props.perfil;
};