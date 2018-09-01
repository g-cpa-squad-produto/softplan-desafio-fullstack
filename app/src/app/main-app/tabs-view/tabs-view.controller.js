/**
 * Faz o controle das abas de navegação. Possui um pequeno bug ao dar refresh no navegador, que não posiciona
 * o indicador das abas no local correto.
 */
const users = 'app.main.users';
const processos = 'app.main.processos';
const parecer = 'app.main.parecer';

const STATE_TAB = {
  'app.main.users': 'users',
  'app.main.processos': 'processos',
  'app.main.parecer': 'parecer'
};

export default class TabsViewsController {
    constructor($state) {
        this.$state = $state;
    }

    $onInit() {
        const currentState = this.$state.current.name;
        this.currentNavItem = STATE_TAB[currentState];
    }

    onClickUsers() {
        this.redirectToState(users);
    }

    onClickProcessos() {
        this.redirectToState(processos);
    }

    onClickParecer() {
        this.redirectToState(parecer);
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