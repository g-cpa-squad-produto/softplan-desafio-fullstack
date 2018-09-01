import angular from 'angular';

import passwordValidator from 'simple-password-validator';
import chlToasts from './toasts/toasts.module';
import dialogModule from './dialog/dialog.module';

import chlLoading from './chl-loading/chl-loading.directive';
import chlDialogHeader from './chl-dialog-header/chl-dialog-header.directive';

const MODULE_NAME = 'chl.core.commons';

angular.module(MODULE_NAME, [passwordValidator, chlToasts, dialogModule])
    .directive('chlLoading', chlLoading.create)
    .component('chlDialogHeader', chlDialogHeader);

export default MODULE_NAME;