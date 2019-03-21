import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../admin.service';
import {ok} from 'assert';

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.css']
})
export class CreateFormComponent implements OnInit {
  name: any;
  email: any;
  mobile: any;
  type: any = 2;
  passwod: any;
  message: any;

  errors = [];

  constructor(private adminService: AdminService) {
  }

  ngOnInit() {
  }

  create() {
    this.errors = [];
    this.message = null;
    if (this.validate()) {

      this.adminService.createUser(this.name, this.email, this.mobile, this.passwod, this.type)
        .subscribe(res => {
          console.log(res);
          if (res['success']) {
            this.message = res['message'];
          } else {
            Object.keys(res['errors']).forEach((key) => {
              this.errors.push(res['errors'][key][0]);
              console.log(this.errors);
            });
          }
        });

    }
  }

  validate() {
    let Ok = true;
    if (this.name == '' || this.name == null) {
      this.errors.push('Name is Required');
      Ok = false;
    }
    if (this.email == '' || this.email == null) {
      this.errors.push('Email is Required');
      Ok = false;
    }
    if (this.mobile == '' || this.mobile == null) {
      this.errors.push('Mobile is Required');
      Ok = false;
    }
    if (this.type == '' || this.type == null) {
      this.errors.push('Type is Required');
      Ok = false;
    }
    if (this.type == '' || this.type == null) {
      this.errors.push('Type is Required');
      Ok = false;
    }
    if (this.passwod == '' || this.passwod == null) {
      this.errors.push('Password is Required');
      Ok = false;
    }
    return Ok;
  }
}
