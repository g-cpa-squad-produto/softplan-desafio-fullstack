export default class ProcessoService {

  constructor(resource) {
    let customActions = {
      listarPendentesParecerUsuario: {
        method: 'GET',
        url: 'processos/usuarios/pendentes'
      }
    }
    this._resource = resource('processos{/id}/',{},customActions)
  }

  salvar(processo) {

    if (processo.id) {
      return this._resource.update({
        id: processo.id
      }, processo);
    } else {
      return this._resource.save(processo);
    }

  }

  listar() {

    return this._resource
      .query()
      .then(res => res.json(), err => {
        console.log(err);
        throw new Error('Não foi possivel listar os Processos!');
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
        throw new Error('Não foi possivel remover o Processo!');
      });
  }

  listarPendentesParecerUsuario() {

    return this._resource
      .listarPendentesParecerUsuario()
      .then(res => res.json(), err => {
        console.log(err);
        throw new Error('Não foi possivel listar os processos!');
      });

  }

}