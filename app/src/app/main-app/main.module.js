import angular from 'angular';

import usersModule from './users/users.module';
import processosModule from './processos/processos.module';

import mainStateConfig from './main.state';

import ToolbarAppController from './toolbar-app/toolbar-app.controller'
import TabsViewController from './tabs-view/tabs-view.controller';

const MODULE_NAME = 'app.main';

angular.module(MODULE_NAME, [usersModule, processosModule])
    .config(mainStateConfig)
    .controller('chl.TabsViewController', TabsViewController)
    .controller('chl.ToolbarAppController', ToolbarAppController);

export default MODULE_NAME;