import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import {AuthService} from './auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  readonly url = 'http://fetcher-app.com/api/admin/';
  public getCompanies() {
    return this.http.get(`${this.url}companies`);
  }
  public getPeople() {
    return this.http.get(`${this.url}people`);
  }
  public getUnActivatedUsers(){
    return this.http.get(`${this.url}unActivatedUsers`,this.getOptions());
  }
  public activateUser(user_id) {
    const fd = new FormData();
    fd.append('user_id', user_id);
    return this.http.post(`${this.url}activateUser`, fd, this.getOptions());
  }
  public deletePerson(person_id) {
    const fd = new FormData();
    fd.append('person_id', person_id);
    return this.http.post(`${this.url}deletePerson`, fd, this.getOptions());
  }
  public deleteCompany(company_id) {
    const fd = new FormData();
    fd.append('company_id', company_id);
    return this.http.post(`${this.url}deleteCompany`, fd, this.getOptions());
  }
  public createUser(name, email, mobile, password, type) {
    const fd = new FormData();
    fd.append('name', name);
    fd.append('email', email);
    fd.append('mobile', mobile);
    fd.append('password', password);
    fd.append('type', type);
    return this.http.post(`${this.url}addUser`, fd, this.getOptions());
  }
  private getOptions() {
    const httpOptions = {
      headers: {
        'Authorization': this.auth.getToken(),
      }
    };
    return httpOptions;
  }
}
