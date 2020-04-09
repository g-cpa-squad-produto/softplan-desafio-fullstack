import axios from "axios";
import Cookies from "universal-cookie";

let instance = false;

const cookies = new Cookies();

export default class AxiosConfig {
  constructor() {
    if (!instance) {
      instance = axios.create({
        baseURL: 'http://localhost:8080/',
        timeout: 15000,
        headers: {
          "Content-Type": "application/json"
        }
      });
    }

    instance.defaults.headers.common.Authorization = cookies.get("Authorization");

    return instance;
  }
}
