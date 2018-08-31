/**
 * Componente responsável por adicionar um loading no início do elemento e enconder o restante
 * Faz uso do scope da controller que está utilizando o componente.
 */

import _ from 'lodash';

import loadingTemplate from './chl-loading.directive.html';

export default class chlLoading {
    constructor($parse, $compile) {
        this.$parse = $parse;
        this.$compile = $compile;

        this.restrict = 'A';
    }

    static create() {
        return new chlLoading(...arguments);
    }

    link(scope, element, attrs) {
        const loadingAttr = attrs.chlLoading;

        if (!loadingAttr) {
            throw new Error('O tracker deve ser passado para a diretiva de loading');
        }

        const trackObject = _.get(scope, loadingAttr);

        if (!trackObject) {
            throw new Error('O tracker não é um objeto válido');
        }

        const loadingElement = this.$compile(loadingTemplate)(scope);

        scope.$watch(`${loadingAttr}.tracking()`, (newValue) => {
            if (newValue) {
                addLoading();
            } else {
                removeLoading();
            }
        });

        function addLoading() {
            element.prepend(loadingElement);
        }

        function removeLoading() {
            loadingElement.remove();
        }
    }
}

chlLoading.create.$inject = ['$parse', '$compile'];