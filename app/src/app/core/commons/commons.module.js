import angular from 'angular';

import chlToasts from './toasts/toasts.module';

import chlLoading from './chl-loading/chl-loading.directive';

const MODULE_NAME = 'chl.core.commons';

angular.module(MODULE_NAME, [chlToasts])
    .directive('chlLoading', chlLoading.create);

export default MODULE_NAME;