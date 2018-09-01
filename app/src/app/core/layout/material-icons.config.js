/**
 * Configuração dos icones disponíveis na aplicação. Os icones que são importados podem ser
 * utilizados posteriormente com o md-icon
 */
export default function materialIconsConfig($mdIconProvider) {
    $mdIconProvider
        .defaultFontSet( 'fa' )
        .icon('account', '/icons/account.svg', 24)
        .icon('delete', '/icons/delete.svg', 24)
        .icon('pencil', '/icons/pencil.svg', 24)
        .icon('close', '/icons/close.svg', 24)
        .icon('exit', '/icons/exit.svg', 24)
        .icon('info', '/icons/info.svg', 24)
        .icon('plus', '/icons/plus.svg', 24);
}

materialIconsConfig.$inject = ['$mdIconProvider'];