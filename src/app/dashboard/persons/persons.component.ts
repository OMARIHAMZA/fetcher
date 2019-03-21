import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../admin.service';

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  constructor(private adminService: AdminService) { }
  public people;
  public message;
  ngOnInit() {
    this.get();
  }
  public get() {
    this.adminService.getPeople().subscribe((res) => {
      this.people = res['data'];
    });
  }

  delete(id: any , index) {
    this.message = null;
    this.adminService.deletePerson(id).subscribe( res => {
      this.message = res['message'];
      if (res['success']) {
        this.people.splice(index, 1);
      }
    });
  }

}

