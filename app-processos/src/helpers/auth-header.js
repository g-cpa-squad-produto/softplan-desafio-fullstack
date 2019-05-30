import { ApplicationContext } from '../components/application-context';

export function authHeader() {
    let user = ApplicationContext.getUser();

    if (user && user.loginAcesso) {
        return { 'Authorization': 'Basic ' + user.loginAcesso };
    } else {
        return {};
    }
}