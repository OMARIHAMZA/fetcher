import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private token = null;
  private user = null;
  readonly url = 'http://127.0.0.1:8000/api/auth/';

  public login(email, password) {
    const fd = new FormData();
    fd.append('email', email);
    fd.append('password', password);
    return this.http.post(`${this.url}login`, fd);
  }

  public refresh() {
    const fd = new FormData();
    return this.http.post(`${this.url}refresh`, fd, this.getOptions());
  }

  public me() {
    const fd = new FormData();
    return this.http.post(`${this.url}me`, fd, this.getOptions());
  }

  public logout() {
    const fd = new FormData();
    return this.http.post(`${this.url}logout`, fd, this.getOptions());
  }
  public authenticated(): boolean{
    return this.user != null;
  }
  public setUser(user) {
    this.user = user;
  }
  public getUser() {
    return this.user;
  }
  public getToken() {
    return this.token;
  }
  public setToken(token) {
    this.token = token;
  }
  private getOptions() {
    if (this.getToken() !== null) {
      return {
        headers: {
          'Accept': 'application/json',
          'Authorization': this.getToken(),
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      };
    } else {
      return {
        headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      };
    }
  }
}
