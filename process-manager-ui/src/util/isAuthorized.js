import { ACCESS_TOKEN } from "../constants";

export default function isAuthorized(values) {
  const acessToken = localStorage.getItem(ACCESS_TOKEN);
  if (acessToken) {
    return true;
  }
  return false;
}
