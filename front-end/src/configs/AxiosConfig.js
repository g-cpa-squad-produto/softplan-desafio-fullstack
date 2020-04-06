import axios from "axios";

let instance = false;

export default class AxiosConfig {
  constructor() {
    if (!instance) {
      instance = axios.create({
        baseURL: process.env.REACT_APP_URL,
        timeout: 15000,
        headers: {
          "Content-Type": "application/json"
        }
      });
    }

    return instance;
  }
}
