/**
 * Configuração dos icones disponíveis na aplicação. Os icones que são importados podem ser
 * utilizados posteriormente com o md-icon
 */
export default function materialIconsConfig($mdIconProvider) {
    $mdIconProvider
        .defaultFontSet( 'fa' )
        .icon('account', '/icons/account.svg', 24);
}

materialIconsConfig.$inject = ['$mdIconProvider'];