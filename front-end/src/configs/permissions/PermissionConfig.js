import Cookies from "universal-cookie";
import AdminRoutes from '../../private/Admin';
import TriatorRoutes from '../../private/Triator';
import FinisherRoutes from '../../private/Finisher';

const cookies = new Cookies();

export default class PermissionConfig {
  constructor() {
    this.permissions = {
      ADMIN: AdminRoutes,
      TRIATOR: TriatorRoutes,
      FINISHER: FinisherRoutes,
    }
    
    return this.permissions[cookies.get('userType')];
  }
}