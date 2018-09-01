import angular from 'angular';

import DialogService from './dialog.service';

const MODULE_NAME = 'chl.core.commons.dialog';

angular.module(MODULE_NAME, [])
    .service('$dialogService', DialogService);

export default MODULE_NAME;