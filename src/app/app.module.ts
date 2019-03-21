import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SidenavComponent } from './dashboard/sidenav/sidenav.component';
import { CreateFormComponent } from './dashboard/create-form/create-form.component';
import { CompaniesComponent } from './dashboard/companies/companies.component';
import { PersonsComponent } from './dashboard/persons/persons.component';
import {AdminService} from './admin.service';
import {AuthService} from './auth/auth.service';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const appRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent , children: [
      { path: 'companies', component: CompaniesComponent },
      { path: 'people',      component: PersonsComponent },
      { path: 'create',      component: CreateFormComponent },
      { path: '',
        redirectTo: 'companies',
        pathMatch: 'full'
      },
  ]},
  { path: 'auth/login', component: LoginComponent },
  { path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
];

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    CreateFormComponent,
    CompaniesComponent,
    PersonsComponent,
    LoginComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } // <-- debugging purposes only
    ),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
