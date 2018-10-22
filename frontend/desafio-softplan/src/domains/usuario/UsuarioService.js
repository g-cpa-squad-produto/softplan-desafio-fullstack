export default class UsuarioService {

  constructor(resource) {
    let customActions = {
      listarUsuariosFinalizadores: {
        method: 'GET',
        url: 'usuarios/finalizadores/'
      }
    }
    this._resource = resource('usuarios{/id}/{?q}', {}, customActions)
  }

  salvar(usuario) {

    if (usuario.id) {
      return this._resource.update({
        id: usuario.id
      }, usuario);
    } else {
      return this._resource.save(usuario);
    }

  }

  listar(q) {

    return this._resource
      .query(q)
      .then(res => res.json(), err => {
        console.log(err);
        throw new Error('Não foi possivel listar os usuarios!');
      });

  }

  trazer(id) {
    return this._resource
      .get({
        id
      })
      .then(res => res.json());
  }

  remover(id) {

    return this._resource
      .delete({
        id
      })
      .then(null, err => {
        console.log(err);
        throw new Error('Não foi possivel remover o usuario!');
      });
  }

  listarFinalizadores() {

    return this._resource
      .listarUsuariosFinalizadores()
      .then(res => res.json(), err => {
        console.log(err);
        throw new Error('Não foi possivel listar os usuarios!');
      });

  }

}