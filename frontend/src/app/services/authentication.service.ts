import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { User } from '../models/user.model';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(email: string, password: string): Observable<any> {
        const href = `${environment.api.url}/users/login`;
        return this.http.post<any>(`${href}`, { email, password})
            .pipe(map((data) => {
                if (data) {
                    localStorage.setItem('access_token', JSON.stringify(data));
                    return true;
                }
                return false;
            }));
    }

    logout(): void {
        localStorage.clear();
    }

    getUser(): User {
        if (this.isLoggedIn()) {
            let user: any = localStorage.getItem('access_token');
            user = JSON.parse(user);
            return new User(user);
        }
    }

    isAuthenticated(): boolean {
        return !!localStorage.getItem('access_token');
    }

    isLoggedIn(): boolean {
        return this.isAuthenticated();
    }

    isLoggedOut(): boolean {
        console.log(!this.isLoggedIn());
        return !this.isLoggedIn();
    }
}