/**
 * Módulo responsável por criar toasts e fornecer facilitadores para a chamada dos mesmos
 * */

import angular from 'angular';

import './toasts.css';

import ToastService from './toast.service';
import ClosableToastController from './closable-toast.controller';

const MODULE_NAME = 'chl.toasts';

angular.module(MODULE_NAME, [
])
    .service('$chlToastService', ToastService)
    .controller('$chlClosableToastController', ClosableToastController);

export default MODULE_NAME;