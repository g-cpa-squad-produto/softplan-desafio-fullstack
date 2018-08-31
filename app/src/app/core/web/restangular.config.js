//Seta URL base para consultas com Restangular
export default function restangularConfig(RestangularProvider, API_URL) {
    RestangularProvider.setBaseUrl(API_URL);
}

restangularConfig.$inject = ['RestangularProvider', 'API_URL'];