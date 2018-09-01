import angular from 'angular';

import processosStateConfig from './processos.state';
import ProcessosController from './processos.controller';

const MODULE_NAME = 'chl.processos';

angular.module(MODULE_NAME, [])
    .config(processosStateConfig)
    .controller('chl.ProcessosController', ProcessosController);

export default MODULE_NAME;