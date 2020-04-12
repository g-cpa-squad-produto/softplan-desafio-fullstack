import jwt from "jsonwebtoken";
import { ACCESS_TOKEN, TOKEN_TYPE } from "../constants";

export function getToken() {
  return `${localStorage.getItem(TOKEN_TYPE)} ${localStorage.getItem(ACCESS_TOKEN)}`;
}

export function getUser() {
  const decodedToken = jwt.decode(localStorage.getItem(ACCESS_TOKEN));
  const user = { ...decodedToken.user, authorities: decodedToken.user.authorities.map((role) => role.authority) };
  return user;
}
