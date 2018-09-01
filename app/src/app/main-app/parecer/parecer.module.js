import angular from 'angular';

import parecerStateConfig from './parecer.state';
import ParecerService from './parecer.service';
import ParecerController from './parecer.controller';
import CadastroParecerController from './cadastro/cadastro-parecer.controller';

const MODULE_NAME = 'chl.parecer';

angular.module(MODULE_NAME, [])
    .config(parecerStateConfig)
    .service('$parecerService', ParecerService)
    .controller('chl.ParecerController', ParecerController)
    .controller('chl.CadastroParecerController', CadastroParecerController);

export default MODULE_NAME;