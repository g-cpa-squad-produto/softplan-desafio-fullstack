import angular from 'angular';

import parecerStateConfig from './parecer.state';
import ParecerController from './parecer.controller';

const MODULE_NAME = 'chl.parecer';

angular.module(MODULE_NAME, [])
    .config(parecerStateConfig)
    .controller('chl.ParecerController', ParecerController);

export default MODULE_NAME;