import { User } from './../model/user.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PROCESS_ADMIN_API } from './process-admin.api';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(user: User) {
    return this.http.post(`${PROCESS_ADMIN_API}/api/auth`, user);
  }

  createOrUpdate(user: User) {
    if (user.id != null && user.id != '') {
      return this.http.put(`${PROCESS_ADMIN_API}/api/user`, user);
    } else {
      user.id = null;
      return this.http.post(`${PROCESS_ADMIN_API}/api/user`, user);
    }
  }

  findAll(page: number, count: number) {
    return this.http.get(`${PROCESS_ADMIN_API}/api/user/${page}/${count}`);
  }

  findById(id: string) {
    return this.http.get(`${PROCESS_ADMIN_API}/api/user/${id}`);
  }

  delete(id: string) {
    return this.http.delete(`${PROCESS_ADMIN_API}/api/user/${id}`);
  }

  findAllReviews() {
    return this.http.get(`${PROCESS_ADMIN_API}/api/user/reviews`);
  }
}
