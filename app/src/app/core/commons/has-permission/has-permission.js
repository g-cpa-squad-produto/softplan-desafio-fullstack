export default class HasPermission {
    constructor($userService) {
        this.$userService = $userService;

        this.restrict = 'A';
    }

    static create() {
        return new HasPermission(...arguments);
    }

    link(scope, element, attrs) {
        const currentUser = this.$userService.getUserValue();
        const permission = attrs.hasPermission;

        if (currentUser.role !== permission) {
            element.addClass('permissions-hide');
        }
    }
}

HasPermission.create.$inject = ['$userService'];