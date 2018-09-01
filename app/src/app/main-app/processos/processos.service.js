export default class ProcessosService {
    constructor(Restangular) {
        this.Restangular = Restangular;
    }

    getAll() {
        return this.Restangular.all('/processos').get('', {});
    }

    get(id) {
        return this.Restangular.all('/processos').get(id);
    }

    save(processo) {
        return this.Restangular.all('/processos').customPOST(processo);
    }

    vinculaveis(id) {
        return this.Restangular.one('/processos', id).getList('vinculaveis', {});
    }
}

ProcessosService.$inject = ['Restangular'];