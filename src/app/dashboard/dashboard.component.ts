import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
    if (!this.auth.authenticated()) {
      this.router.navigate(['auth/login']);
    }
  }

  public logout() {
    this.auth.logout().subscribe(res => {
      if ( res['success'] ) {
        this.auth.setUser(null);
        this.auth.setToken(null);
        this.router.navigate(['auth/login']);
      }
    });
  }
}
