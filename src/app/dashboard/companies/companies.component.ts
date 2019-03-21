import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../admin.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  constructor(private adminService: AdminService) { }
  public companies;
  public message;
  ngOnInit() {
    this.get();
  }
  public get() {
    this.adminService.getCompanies().subscribe((res) => {
      this.companies = res['data'];
    });
  }

  delete(id: any , index) {
    this.message = null;
    this.adminService.deleteCompany(id).subscribe( res => {
        this.message = res['message'];
        if (res['success']) {
          this.companies.splice(index, 1);
        }
    });
  }
}
