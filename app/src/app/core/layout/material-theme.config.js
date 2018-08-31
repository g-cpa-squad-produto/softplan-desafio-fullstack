/**
 * Config que define as cores da aplicação.
 */
export default function materialThemeConfig($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('blue')
        .accentPalette('deep-orange')
        .warnPalette('red');
}

materialThemeConfig.$inject = ['$mdThemingProvider'];