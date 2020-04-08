import Cookies from "universal-cookie";
import AdminRoutes from "../domain/private/Admin";
import TriatorRoutes from "../domain/private/Triator";
import FinisherRoutes from "../domain/private/Finisher";

const cookies = new Cookies();

export default class PermissionConfig {
  constructor() {
    this.permissions = {
      ADMIN: AdminRoutes,
      TRIATOR: TriatorRoutes,
      FINISHER: FinisherRoutes,
    };

    return this.permissions[cookies.get("userType")];
  }
}
