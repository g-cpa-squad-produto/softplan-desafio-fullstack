/**
 * Faz o controle das abas de navegação. Possui um pequeno bug ao dar refresh no navegador, que não posiciona
 * o indicador das abas no local correto.
 */
const users = 'app.main.users';
const processos = 'app.main.processos';

export default class TabsViewsController {
    constructor($state) {
        this.$state = $state;
    }

    $onInit() {
        this.currentNavItem = 'users'
    }

    onClickUsers() {
        this.redirectToState(users);
    }

    onClickProcessos() {
        this.redirectToState(processos);
    }

    redirectToState(targetState) {
        if (this.$state.current.name === targetState) {
            return;
        }

        this.$state.go(targetState);
    }
}

TabsViewsController.$inject = [
    '$state'
];