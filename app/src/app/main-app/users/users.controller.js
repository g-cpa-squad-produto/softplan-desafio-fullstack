import modalTemplate from './cadastro/cadastro-user.html';

export default class UsersController {
    constructor(promiseTracker, $usersService, $userService,
                $dialogService, $chlToastService, USER_PROFILE) {
        this.promiseTracker = promiseTracker;
        this.$usersService = $usersService;
        this.$userService = $userService;
        this.$dialogService = $dialogService;
        this.$chlToastService = $chlToastService;
        this.USER_PROFILE = USER_PROFILE;
    }

    $onInit() {
        this.tracker = {
            list: this.promiseTracker()
        };

        this.currentUser = this.$userService.getUserValue();

        this.fetchUsers();
    }

    fetchUsers() {
        const promise = this.$usersService.getAll()
            .then(users => {
                this.users = users;
            });
        this.tracker.list.addPromise(promise);
    }

    onClickCriar($event) {
        this.openModalCadastro($event);
    }

    onClickEditar($event, user) {
        this.openModalCadastro($event, {
            user: this.$usersService.get(user.id)
        })
    }

    onClickRemover($event, user) {
        this.$dialogService.showConfirm({
            text: 'Esta ação não poderá ser desfeita',
            targetEvent: $event
        })
            .then(()=> {
                this.tableTracker =
                    this.$usersService.remove(user.id)
                        .then(() => {
                            this.fetchUsers();
                            this.$chlToastService.showCustom('Registro excluído com sucesso', 'success');
                        });
            });
    }

    openModalCadastro($event, locals) {
        this.$dialogService.showDefault({
            template: modalTemplate,
            controller: 'chl.CadastroUserController',
            targetEvent: $event,
            locals
        }).then(() => {
            this.fetchUsers();
        })
    }
}

UsersController.$inject = [
    'promiseTracker',
    '$usersService',
    '$userService',
    '$dialogService',
    '$chlToastService',
    'USER_PROFILE'
];