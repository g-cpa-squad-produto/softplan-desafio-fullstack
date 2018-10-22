export default class PorcessoService {

  constructor(resource) {
    let customActions = {
      salvarParecerProcesso: {
        method: 'POST',
        url: 'pareceres/processo'
      }
    }
    this._resource = resource('pareceres{/id}/',{},customActions)
  }

  salvar(parecer) {

    if (parecer.id) {
      return this._resource.update({
        id: parecer.id
      }, parecer);
    } else {
      return this._resource.save(parecer);
    }

  }

  listar() {

    return this._resource
      .query()
      .then(res => res.json(), err => {
        console.log(err);
        throw new Error('Não foi possivel listar os pareceres!');
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
        throw new Error('Não foi possivel remover o Parecer!');
      });
  }

  salvarParecerProcesso(parecer) {
    return this._resource.salvarParecerProcesso(parecer);
  }

  

}