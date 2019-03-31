import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../admin.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public message;
  public users;
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getUnActivatedUsers();
  }

  public getUnActivatedUsers(){
    this.adminService.getUnActivatedUsers().subscribe((res) => {
      this.users = res['data'];
    });
  }
  public activateUser(user_id, index) {
    this.message = null;
    this.adminService.activateUser(user_id).subscribe( res => {
      this.message = res['message'];
      if (res['success']) {
        this.users.splice(index, 1);
      }
    });
  }

}
