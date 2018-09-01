import angular from 'angular';

import processosStateConfig from './processos.state';
import ProcessosService from './processos.service';
import ProcessosController from './processos.controller';
import CadastroProcesso from './cadastro/cadastro-processos.controller';
import VinculoController from './vinculo/vinculo.controller';

const MODULE_NAME = 'chl.processos';

angular.module(MODULE_NAME, [])
    .config(processosStateConfig)
    .service('$processosService', ProcessosService)
    .controller('chl.ProcessosController', ProcessosController)
    .controller('chl.CadastroProcesso', CadastroProcesso)
    .controller('chl.VinculoController', VinculoController);

export default MODULE_NAME;