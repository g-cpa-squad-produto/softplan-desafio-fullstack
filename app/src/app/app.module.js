import angular from 'angular';
import uiRouter from '@uirouter/angularjs';
import 'angular-material/index'

import 'angular-material/angular-material.min.css'
import '../style/app.css';

import coreModule from './core/core.module';
import appConstantsModule from './app.constants';
import loginModule from './login/login.module';
import mainModule from './main-app/main.module';

import stateConfig from './app.state';

const MODULE_NAME = 'app';

angular.module(MODULE_NAME, [
    appConstantsModule,
    uiRouter,
    coreModule,
    loginModule,
    mainModule
])
    .config(stateConfig);

export default MODULE_NAME;