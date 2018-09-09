import {apiAuth} from '../services/ApiService';
import AuthService from "./AuthService";

export const authService = new AuthService(apiAuth);