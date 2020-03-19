import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from "../../../environments/environment";
import { User } from "../../domain/entity/user";

@Injectable({ providedIn: "root" })
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    return this.http
      .post<any>(environment.apiUrl + "login?username=" + username + "&password=" + password, undefined).toPromise();
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem("currentToken");
    localStorage.removeItem("currentUser");
  }
}
