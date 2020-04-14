import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor(private http: HttpClient) {}

    listUsers(): Observable<any> {
        return this.http.get<any>(`${environment.api.url}/users`);
    }

    getUser(id: string): Observable<User> {
        console.log(id);
        return this.http.get<User>(`${environment.api.url}/users/${id}`);
    }

    createUser(user: User): Observable<any> {
        console.log(user);
        const href = `${environment.api.url}/users`;
        return this.http.post<any>(`${href}`, {...user});
    }

    updateUser(id: number, user: User): Observable<any> {
        const href = `${environment.api.url}/users/${id}`;
        return this.http.put<any>(`${href}`, {...user});
    }

    deleteUser(id: number): Observable<User> {
        return this.http.delete<User>(`${environment.api.url}/users/${id}`);
    }

    getRoles(): Observable<any> {
        const mock = [
            {
                'label': 'Administrador',
                'name': 'ADMIN'
            },
            {
                'label': 'Usuário Finalizador',
                'name': 'FINALIZADOR'
            },
            {
                'label': 'Usuário Triador',
                'name': 'TRIADOR'
            }
        ];

        return of(mock);
    }
}
