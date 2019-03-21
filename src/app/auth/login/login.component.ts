import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }
  public email;
  public password;
  public message: string = null;
  ngOnInit() {
  }
  public login() {
    this.message = null;
    this.auth.login(this.email, this.password).subscribe(res => {
      if (res['success']) {
        this.auth.setToken('bearer ' + res['access_token']);
        // Make Sure It's Admin
        this.me();
      } else {
        this.message = res['message'];
      }
    }, err => {
      this.message = 'Invalid Credentials';
    });
  }
  public me() {
    this.auth.me().subscribe( me => {
      if (me['success']) {
        const user = me['user'];
        if (user.type === '1') {
          this.auth.setUser(user);
          this.router.navigate(['/']);
        } else {
          this.auth.setToken(null);
          this.auth.setUser(null);
          this.message = 'Invalid Credentials';
        }
      }
    });
  }
}
