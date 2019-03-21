import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  constructor() { }
  public selected = 'companies';
  public isSelected(tab): boolean {
    return tab === this.selected;
  }
  public select(tab) {
    this.selected = tab;
  }
  public getTabClass(tab) {
    if (this.isSelected(tab)) {
      return 'nav-link active';
    } else {
      return 'nav-link';
    }
  }
  ngOnInit() {
  }

}
