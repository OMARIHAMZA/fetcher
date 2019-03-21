(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/admin.service.ts":
/*!**********************************!*\
  !*** ./src/app/admin.service.ts ***!
  \**********************************/
/*! exports provided: AdminService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AdminService", function() { return AdminService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _auth_auth_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./auth/auth.service */ "./src/app/auth/auth.service.ts");




var AdminService = /** @class */ (function () {
    function AdminService(http, auth) {
        this.http = http;
        this.auth = auth;
        this.url = 'http://127.0.0.1:8000/api/admin/';
    }
    AdminService.prototype.getCompanies = function () {
        return this.http.get(this.url + "companies");
    };
    AdminService.prototype.getPeople = function () {
        return this.http.get(this.url + "people");
    };
    AdminService.prototype.deletePerson = function (person_id) {
        var fd = new FormData();
        fd.append('person_id', person_id);
        return this.http.post(this.url + "deletePerson", fd, this.getOptions());
    };
    AdminService.prototype.deleteCompany = function (company_id) {
        var fd = new FormData();
        fd.append('company_id', company_id);
        return this.http.post(this.url + "deleteCompany", fd, this.getOptions());
    };
    AdminService.prototype.createUser = function (name, email, mobile, password, type) {
        var fd = new FormData();
        fd.append('name', name);
        fd.append('email', email);
        fd.append('mobile', mobile);
        fd.append('password', password);
        fd.append('type', type);
        return this.http.post(this.url + "addUser", fd, this.getOptions());
    };
    AdminService.prototype.getOptions = function () {
        var httpOptions = {
            headers: {
                'Authorization': this.auth.getToken(),
            }
        };
        return httpOptions;
    };
    AdminService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _auth_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"]])
    ], AdminService);
    return AdminService;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<router-outlet></router-outlet>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'admin-app';
    }
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _dashboard_sidenav_sidenav_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./dashboard/sidenav/sidenav.component */ "./src/app/dashboard/sidenav/sidenav.component.ts");
/* harmony import */ var _dashboard_create_form_create_form_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./dashboard/create-form/create-form.component */ "./src/app/dashboard/create-form/create-form.component.ts");
/* harmony import */ var _dashboard_companies_companies_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./dashboard/companies/companies.component */ "./src/app/dashboard/companies/companies.component.ts");
/* harmony import */ var _dashboard_persons_persons_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./dashboard/persons/persons.component */ "./src/app/dashboard/persons/persons.component.ts");
/* harmony import */ var _auth_login_login_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./auth/login/login.component */ "./src/app/auth/login/login.component.ts");
/* harmony import */ var _dashboard_dashboard_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./dashboard/dashboard.component */ "./src/app/dashboard/dashboard.component.ts");













var appRoutes = [
    { path: 'dashboard', component: _dashboard_dashboard_component__WEBPACK_IMPORTED_MODULE_12__["DashboardComponent"], children: [
            { path: 'companies', component: _dashboard_companies_companies_component__WEBPACK_IMPORTED_MODULE_9__["CompaniesComponent"] },
            { path: 'people', component: _dashboard_persons_persons_component__WEBPACK_IMPORTED_MODULE_10__["PersonsComponent"] },
            { path: 'create', component: _dashboard_create_form_create_form_component__WEBPACK_IMPORTED_MODULE_8__["CreateFormComponent"] },
            { path: '',
                redirectTo: 'companies',
                pathMatch: 'full'
            },
        ] },
    { path: 'auth/login', component: _auth_login_login_component__WEBPACK_IMPORTED_MODULE_11__["LoginComponent"] },
    { path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
];
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
                _dashboard_sidenav_sidenav_component__WEBPACK_IMPORTED_MODULE_7__["SidenavComponent"],
                _dashboard_create_form_create_form_component__WEBPACK_IMPORTED_MODULE_8__["CreateFormComponent"],
                _dashboard_companies_companies_component__WEBPACK_IMPORTED_MODULE_9__["CompaniesComponent"],
                _dashboard_persons_persons_component__WEBPACK_IMPORTED_MODULE_10__["PersonsComponent"],
                _auth_login_login_component__WEBPACK_IMPORTED_MODULE_11__["LoginComponent"],
                _dashboard_dashboard_component__WEBPACK_IMPORTED_MODULE_12__["DashboardComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"].forRoot(appRoutes, { enableTracing: false } // <-- debugging purposes only
                ),
                _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClientModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"]
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/auth/auth.service.ts":
/*!**************************************!*\
  !*** ./src/app/auth/auth.service.ts ***!
  \**************************************/
/*! exports provided: AuthService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthService", function() { return AuthService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");



var AuthService = /** @class */ (function () {
    function AuthService(http) {
        this.http = http;
        this.token = null;
        this.user = null;
        this.url = 'http://127.0.0.1:8000/api/auth/';
    }
    AuthService.prototype.login = function (email, password) {
        var fd = new FormData();
        fd.append('email', email);
        fd.append('password', password);
        return this.http.post(this.url + "login", fd);
    };
    AuthService.prototype.refresh = function () {
        var fd = new FormData();
        return this.http.post(this.url + "refresh", fd, this.getOptions());
    };
    AuthService.prototype.me = function () {
        var fd = new FormData();
        return this.http.post(this.url + "me", fd, this.getOptions());
    };
    AuthService.prototype.logout = function () {
        var fd = new FormData();
        return this.http.post(this.url + "logout", fd, this.getOptions());
    };
    AuthService.prototype.authenticated = function () {
        return this.user != null;
    };
    AuthService.prototype.setUser = function (user) {
        this.user = user;
    };
    AuthService.prototype.getUser = function () {
        return this.user;
    };
    AuthService.prototype.getToken = function () {
        return this.token;
    };
    AuthService.prototype.setToken = function (token) {
        this.token = token;
    };
    AuthService.prototype.getOptions = function () {
        if (this.getToken() !== null) {
            return {
                headers: {
                    'Accept': 'application/json',
                    'Authorization': this.getToken(),
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };
        }
        else {
            return {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };
        }
    };
    AuthService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], AuthService);
    return AuthService;
}());



/***/ }),

/***/ "./src/app/auth/login/login.component.css":
/*!************************************************!*\
  !*** ./src/app/auth/login/login.component.css ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".bd-placeholder-img {\r\n  font-size: 1.125rem;\r\n  text-anchor: middle;\r\n  -webkit-user-select: none;\r\n  -moz-user-select: none;\r\n  -ms-user-select: none;\r\n  user-select: none;\r\n}\r\n\r\n@media (min-width: 768px) {\r\n  .bd-placeholder-img-lg {\r\n    font-size: 3.5rem;\r\n  }\r\n}\r\n\r\n.form-signin {\r\n  width: 100%;\r\n  max-width: 330px;\r\n  padding: 15px;\r\n  margin: auto;\r\n}\r\n\r\n.form-signin .checkbox {\r\n  font-weight: 400;\r\n}\r\n\r\n.form-signin .form-control {\r\n  position: relative;\r\n  box-sizing: border-box;\r\n  height: auto;\r\n  padding: 10px;\r\n  font-size: 16px;\r\n}\r\n\r\n.form-signin .form-control:focus {\r\n  z-index: 2;\r\n}\r\n\r\n.form-signin input[type=\"email\"] {\r\n  margin-bottom: -1px;\r\n  border-bottom-right-radius: 0;\r\n  border-bottom-left-radius: 0;\r\n}\r\n\r\n.form-signin input[type=\"password\"] {\r\n  margin-bottom: 10px;\r\n  border-top-left-radius: 0;\r\n  border-top-right-radius: 0;\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvYXV0aC9sb2dpbi9sb2dpbi5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsbUJBQW1CO0VBQ25CLG1CQUFtQjtFQUNuQix5QkFBeUI7RUFDekIsc0JBQXNCO0VBQ3RCLHFCQUFxQjtFQUNyQixpQkFBaUI7QUFDbkI7O0FBRUE7RUFDRTtJQUNFLGlCQUFpQjtFQUNuQjtBQUNGOztBQUdBO0VBQ0UsV0FBVztFQUNYLGdCQUFnQjtFQUNoQixhQUFhO0VBQ2IsWUFBWTtBQUNkOztBQUNBO0VBQ0UsZ0JBQWdCO0FBQ2xCOztBQUNBO0VBQ0Usa0JBQWtCO0VBQ2xCLHNCQUFzQjtFQUN0QixZQUFZO0VBQ1osYUFBYTtFQUNiLGVBQWU7QUFDakI7O0FBQ0E7RUFDRSxVQUFVO0FBQ1o7O0FBQ0E7RUFDRSxtQkFBbUI7RUFDbkIsNkJBQTZCO0VBQzdCLDRCQUE0QjtBQUM5Qjs7QUFDQTtFQUNFLG1CQUFtQjtFQUNuQix5QkFBeUI7RUFDekIsMEJBQTBCO0FBQzVCIiwiZmlsZSI6InNyYy9hcHAvYXV0aC9sb2dpbi9sb2dpbi5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmJkLXBsYWNlaG9sZGVyLWltZyB7XHJcbiAgZm9udC1zaXplOiAxLjEyNXJlbTtcclxuICB0ZXh0LWFuY2hvcjogbWlkZGxlO1xyXG4gIC13ZWJraXQtdXNlci1zZWxlY3Q6IG5vbmU7XHJcbiAgLW1vei11c2VyLXNlbGVjdDogbm9uZTtcclxuICAtbXMtdXNlci1zZWxlY3Q6IG5vbmU7XHJcbiAgdXNlci1zZWxlY3Q6IG5vbmU7XHJcbn1cclxuXHJcbkBtZWRpYSAobWluLXdpZHRoOiA3NjhweCkge1xyXG4gIC5iZC1wbGFjZWhvbGRlci1pbWctbGcge1xyXG4gICAgZm9udC1zaXplOiAzLjVyZW07XHJcbiAgfVxyXG59XHJcblxyXG5cclxuLmZvcm0tc2lnbmluIHtcclxuICB3aWR0aDogMTAwJTtcclxuICBtYXgtd2lkdGg6IDMzMHB4O1xyXG4gIHBhZGRpbmc6IDE1cHg7XHJcbiAgbWFyZ2luOiBhdXRvO1xyXG59XHJcbi5mb3JtLXNpZ25pbiAuY2hlY2tib3gge1xyXG4gIGZvbnQtd2VpZ2h0OiA0MDA7XHJcbn1cclxuLmZvcm0tc2lnbmluIC5mb3JtLWNvbnRyb2wge1xyXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xyXG4gIGhlaWdodDogYXV0bztcclxuICBwYWRkaW5nOiAxMHB4O1xyXG4gIGZvbnQtc2l6ZTogMTZweDtcclxufVxyXG4uZm9ybS1zaWduaW4gLmZvcm0tY29udHJvbDpmb2N1cyB7XHJcbiAgei1pbmRleDogMjtcclxufVxyXG4uZm9ybS1zaWduaW4gaW5wdXRbdHlwZT1cImVtYWlsXCJdIHtcclxuICBtYXJnaW4tYm90dG9tOiAtMXB4O1xyXG4gIGJvcmRlci1ib3R0b20tcmlnaHQtcmFkaXVzOiAwO1xyXG4gIGJvcmRlci1ib3R0b20tbGVmdC1yYWRpdXM6IDA7XHJcbn1cclxuLmZvcm0tc2lnbmluIGlucHV0W3R5cGU9XCJwYXNzd29yZFwiXSB7XHJcbiAgbWFyZ2luLWJvdHRvbTogMTBweDtcclxuICBib3JkZXItdG9wLWxlZnQtcmFkaXVzOiAwO1xyXG4gIGJvcmRlci10b3AtcmlnaHQtcmFkaXVzOiAwO1xyXG59XHJcbiJdfQ== */"

/***/ }),

/***/ "./src/app/auth/login/login.component.html":
/*!*************************************************!*\
  !*** ./src/app/auth/login/login.component.html ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<form class=\"form-signin text-center\" style=\"margin-top: 180px\">\n  <h1 class=\"h3 mb-3 font-weight-normal\">Please sign in</h1>\n  <div class=\"alert alert-danger\" role=\"alert\" *ngIf=\"message\">\n    {{message}}\n  </div>\n  <label for=\"inputEmail\" class=\"sr-only\">Email address</label>\n  <input [(ngModel)]=\"email\" [ngModelOptions]=\"{standalone: true}\" type=\"email\" id=\"inputEmail\" class=\"form-control\" placeholder=\"Email address\" required autofocus>\n  <label for=\"inputPassword\" class=\"sr-only\">Password</label>\n  <input [(ngModel)]=\"password\" [ngModelOptions]=\"{standalone: true}\" type=\"password\" id=\"inputPassword\" class=\"form-control\" placeholder=\"Password\" required>\n  <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" (click)=\"login()\">Sign in</button>\n  <p class=\"mt-5 mb-3 text-muted\">&copy; 2019-2020</p>\n</form>\n"

/***/ }),

/***/ "./src/app/auth/login/login.component.ts":
/*!***********************************************!*\
  !*** ./src/app/auth/login/login.component.ts ***!
  \***********************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _auth_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../auth.service */ "./src/app/auth/auth.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");




var LoginComponent = /** @class */ (function () {
    function LoginComponent(auth, router) {
        this.auth = auth;
        this.router = router;
        this.message = null;
    }
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.login = function () {
        var _this = this;
        this.message = null;
        this.auth.login(this.email, this.password).subscribe(function (res) {
            if (res['success']) {
                _this.auth.setToken('bearer ' + res['access_token']);
                // Make Sure It's Admin
                _this.me();
            }
            else {
                _this.message = res['message'];
            }
        }, function (err) {
            _this.message = 'Invalid Credentials';
        });
    };
    LoginComponent.prototype.me = function () {
        var _this = this;
        this.auth.me().subscribe(function (me) {
            if (me['success']) {
                var user = me['user'];
                if (user.type === '1') {
                    _this.auth.setUser(user);
                    _this.router.navigate(['/']);
                }
                else {
                    _this.auth.setToken(null);
                    _this.auth.setUser(null);
                    _this.message = 'Invalid Credentials';
                }
            }
        });
    };
    LoginComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.component.html */ "./src/app/auth/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.css */ "./src/app/auth/login/login.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_auth_service__WEBPACK_IMPORTED_MODULE_2__["AuthService"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/companies/companies.component.css":
/*!*************************************************************!*\
  !*** ./src/app/dashboard/companies/companies.component.css ***!
  \*************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC9jb21wYW5pZXMvY29tcGFuaWVzLmNvbXBvbmVudC5jc3MifQ== */"

/***/ }),

/***/ "./src/app/dashboard/companies/companies.component.html":
/*!**************************************************************!*\
  !*** ./src/app/dashboard/companies/companies.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"alert alert-warning\" role=\"alert\" *ngIf=\"message\">\n  {{message}}\n</div>\n<table class=\"table table-bordered\">\n  <thead class=\"thead-dark\">\n  <tr>\n    <th scope=\"col\">Name</th>\n    <th scope=\"col\">Email</th>\n    <th scope=\"col\">Website</th>\n    <th scope=\"col\">Mobile</th>\n    <th scope=\"col\">Delete</th>\n  </tr>\n  </thead>\n  <tbody>\n  <tr *ngFor=\"let company of companies;let i = index\">\n    <th scope=\"row\">{{company.name}}</th>\n    <td>{{company.official_email}}</td>\n    <td>{{company.website}}</td>\n    <td>{{company.mobile}}</td>\n    <td><button class=\"btn btn-danger text-light text-center\" (click)=\"delete(company.id, i)\">delete</button></td>\n  </tr>\n  </tbody>\n</table>\n"

/***/ }),

/***/ "./src/app/dashboard/companies/companies.component.ts":
/*!************************************************************!*\
  !*** ./src/app/dashboard/companies/companies.component.ts ***!
  \************************************************************/
/*! exports provided: CompaniesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CompaniesComponent", function() { return CompaniesComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _admin_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../admin.service */ "./src/app/admin.service.ts");



var CompaniesComponent = /** @class */ (function () {
    function CompaniesComponent(adminService) {
        this.adminService = adminService;
    }
    CompaniesComponent.prototype.ngOnInit = function () {
        this.get();
    };
    CompaniesComponent.prototype.get = function () {
        var _this = this;
        this.adminService.getCompanies().subscribe(function (res) {
            _this.companies = res['data'];
        });
    };
    CompaniesComponent.prototype.delete = function (id, index) {
        var _this = this;
        this.message = null;
        this.adminService.deleteCompany(id).subscribe(function (res) {
            _this.message = res['message'];
            if (res['success']) {
                _this.companies.splice(index, 1);
            }
        });
    };
    CompaniesComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-companies',
            template: __webpack_require__(/*! ./companies.component.html */ "./src/app/dashboard/companies/companies.component.html"),
            styles: [__webpack_require__(/*! ./companies.component.css */ "./src/app/dashboard/companies/companies.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_admin_service__WEBPACK_IMPORTED_MODULE_2__["AdminService"]])
    ], CompaniesComponent);
    return CompaniesComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/create-form/create-form.component.css":
/*!*****************************************************************!*\
  !*** ./src/app/dashboard/create-form/create-form.component.css ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC9jcmVhdGUtZm9ybS9jcmVhdGUtZm9ybS5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/dashboard/create-form/create-form.component.html":
/*!******************************************************************!*\
  !*** ./src/app/dashboard/create-form/create-form.component.html ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <div class=\"alert alert-warning\" role=\"alert\" *ngIf=\"message\">\n    {{message}}\n  </div>\n  <div class=\"alert alert-danger\" role=\"alert\" *ngFor=\"let single_error of errors\">\n    {{single_error}}\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputName\" class=\"col-sm-2 col-form-label\">Name</label>\n    <div class=\"col-sm-10\">\n      <input minlength=\"8\" [(ngModel)]=\"name\" [ngModelOptions]=\"{standalone: true}\" type=\"text\" class=\"form-control\" id=\"inputName\" placeholder=\"Name\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputEmail\" class=\"col-sm-2 col-form-label\">Email</label>\n    <div class=\"col-sm-10\">\n      <input [(ngModel)]=\"email\" [ngModelOptions]=\"{standalone: true}\" type=\"email\" class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputMobile\" class=\"col-sm-2 col-form-label\">Mobile</label>\n    <div class=\"col-sm-10\">\n      <input [(ngModel)]=\"mobile\" [ngModelOptions]=\"{standalone: true}\" type=\"number\" class=\"form-control\" id=\"inputMobile\" placeholder=\"Mobile\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputType\" class=\"col-sm-2 col-form-label\">Type</label>\n    <div class=\"col-sm-10\">\n      <select class=\"custom-select\" id=\"inputType\" [(ngModel)]=\"type\" [ngModelOptions]=\"{standalone: true}\">\n        <option value=\"2\" selected>Company</option>\n        <option value=\"3\">Person</option>\n      </select>\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputPassword\" class=\"col-sm-2 col-form-label\">Password</label>\n    <div class=\"col-sm-10\">\n      <input [(ngModel)]=\"passwod\" [ngModelOptions]=\"{standalone: true}\" type=\"password\" class=\"form-control\" id=\"inputPassword\" placeholder=\"Password\">\n    </div>\n  </div>\n\n  <div class=\"form-group row\">\n    <div class=\"col-sm-10\">\n      <button type=\"submit\" class=\"btn btn-primary\" (click)=\"create()\">Create</button>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/dashboard/create-form/create-form.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/dashboard/create-form/create-form.component.ts ***!
  \****************************************************************/
/*! exports provided: CreateFormComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateFormComponent", function() { return CreateFormComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _admin_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../admin.service */ "./src/app/admin.service.ts");



var CreateFormComponent = /** @class */ (function () {
    function CreateFormComponent(adminService) {
        this.adminService = adminService;
        this.type = 2;
        this.errors = [];
    }
    CreateFormComponent.prototype.ngOnInit = function () {
    };
    CreateFormComponent.prototype.create = function () {
        var _this = this;
        this.errors = [];
        this.message = null;
        if (this.validate()) {
            this.adminService.createUser(this.name, this.email, this.mobile, this.passwod, this.type)
                .subscribe(function (res) {
                console.log(res);
                if (res['success']) {
                    _this.message = res['message'];
                }
                else {
                    Object.keys(res['errors']).forEach(function (key) {
                        _this.errors.push(res['errors'][key][0]);
                        console.log(_this.errors);
                    });
                }
            });
        }
    };
    CreateFormComponent.prototype.validate = function () {
        var Ok = true;
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
    };
    CreateFormComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-create-form',
            template: __webpack_require__(/*! ./create-form.component.html */ "./src/app/dashboard/create-form/create-form.component.html"),
            styles: [__webpack_require__(/*! ./create-form.component.css */ "./src/app/dashboard/create-form/create-form.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_admin_service__WEBPACK_IMPORTED_MODULE_2__["AdminService"]])
    ], CreateFormComponent);
    return CreateFormComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/dashboard.component.css":
/*!***************************************************!*\
  !*** ./src/app/dashboard/dashboard.component.css ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC9kYXNoYm9hcmQuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/dashboard/dashboard.component.html":
/*!****************************************************!*\
  !*** ./src/app/dashboard/dashboard.component.html ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<nav class=\"navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow\">\n  <a class=\"navbar-brand col-sm-3 col-md-2 mr-0\" href=\"#\">Fetcher</a>\n  <ul class=\"navbar-nav px-3\">\n    <li class=\"nav-item text-nowrap\">\n      <a class=\"nav-link\" (click)=\"logout()\" style=\"cursor: pointer\">Logout</a>\n    </li>\n  </ul>\n</nav>\n\n<div class=\"container-fluid\">\n  <div class=\"row\">\n    <app-sidenav></app-sidenav>\n\n    <main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 px-4\">\n      <div class=\"d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom\">\n        <h1 class=\"h2\">Dashboard</h1>\n      </div>\n\n      <router-outlet></router-outlet>\n    </main>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/dashboard/dashboard.component.ts":
/*!**************************************************!*\
  !*** ./src/app/dashboard/dashboard.component.ts ***!
  \**************************************************/
/*! exports provided: DashboardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DashboardComponent", function() { return DashboardComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _auth_auth_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../auth/auth.service */ "./src/app/auth/auth.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");




var DashboardComponent = /** @class */ (function () {
    function DashboardComponent(auth, router) {
        this.auth = auth;
        this.router = router;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        if (!this.auth.authenticated()) {
            this.router.navigate(['auth/login']);
        }
    };
    DashboardComponent.prototype.logout = function () {
        var _this = this;
        this.auth.logout().subscribe(function (res) {
            if (res['success']) {
                _this.auth.setUser(null);
                _this.auth.setToken(null);
                _this.router.navigate(['auth/login']);
            }
        });
    };
    DashboardComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-dashboard',
            template: __webpack_require__(/*! ./dashboard.component.html */ "./src/app/dashboard/dashboard.component.html"),
            styles: [__webpack_require__(/*! ./dashboard.component.css */ "./src/app/dashboard/dashboard.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_auth_auth_service__WEBPACK_IMPORTED_MODULE_2__["AuthService"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"]])
    ], DashboardComponent);
    return DashboardComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/persons/persons.component.css":
/*!*********************************************************!*\
  !*** ./src/app/dashboard/persons/persons.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC9wZXJzb25zL3BlcnNvbnMuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/dashboard/persons/persons.component.html":
/*!**********************************************************!*\
  !*** ./src/app/dashboard/persons/persons.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"alert alert-warning\" role=\"alert\" *ngIf=\"message\">\n  {{message}}\n</div>\n<table class=\"table table-bordered\">\n  <thead class=\"thead-dark\">\n  <tr>\n    <th scope=\"col\">Name</th>\n    <th scope=\"col\">Email</th>\n    <th scope=\"col\">Address</th>\n    <th scope=\"col\">Mobile</th>\n    <th scope=\"col\">Delete</th>\n  </tr>\n  </thead>\n  <tbody>\n  <tr *ngFor=\"let persons of people;let i = index\">\n    <th scope=\"row\">{{persons.name}}</th>\n    <td>{{persons.email}}</td>\n    <td>{{persons.address}}</td>\n    <td>{{persons.mobile}}</td>\n    <td><button class=\"btn btn-danger text-light text-center\" (click)=\"delete(persons.id,i)\">delete</button></td>\n  </tr>\n  </tbody>\n</table>\n"

/***/ }),

/***/ "./src/app/dashboard/persons/persons.component.ts":
/*!********************************************************!*\
  !*** ./src/app/dashboard/persons/persons.component.ts ***!
  \********************************************************/
/*! exports provided: PersonsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PersonsComponent", function() { return PersonsComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _admin_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../admin.service */ "./src/app/admin.service.ts");



var PersonsComponent = /** @class */ (function () {
    function PersonsComponent(adminService) {
        this.adminService = adminService;
    }
    PersonsComponent.prototype.ngOnInit = function () {
        this.get();
    };
    PersonsComponent.prototype.get = function () {
        var _this = this;
        this.adminService.getPeople().subscribe(function (res) {
            _this.people = res['data'];
        });
    };
    PersonsComponent.prototype.delete = function (id, index) {
        var _this = this;
        this.message = null;
        this.adminService.deletePerson(id).subscribe(function (res) {
            _this.message = res['message'];
            if (res['success']) {
                _this.people.splice(index, 1);
            }
        });
    };
    PersonsComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-persons',
            template: __webpack_require__(/*! ./persons.component.html */ "./src/app/dashboard/persons/persons.component.html"),
            styles: [__webpack_require__(/*! ./persons.component.css */ "./src/app/dashboard/persons/persons.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_admin_service__WEBPACK_IMPORTED_MODULE_2__["AdminService"]])
    ], PersonsComponent);
    return PersonsComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/sidenav/sidenav.component.css":
/*!*********************************************************!*\
  !*** ./src/app/dashboard/sidenav/sidenav.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC9zaWRlbmF2L3NpZGVuYXYuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/dashboard/sidenav/sidenav.component.html":
/*!**********************************************************!*\
  !*** ./src/app/dashboard/sidenav/sidenav.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<nav class=\"col-md-2 d-none d-md-block bg-light sidebar\">\n  <div class=\"sidebar-sticky\">\n    <ul class=\"nav flex-column\">\n      <li class=\"nav-item\">\n        <a [ngClass]=\"getTabClass('companies')\" (click)=\"select('companies')\" routerLink=\"companies\">\n          Companies\n        </a>\n      </li>\n      <li class=\"nav-item\">\n        <a [ngClass]=\"getTabClass('people')\" (click)=\"select('people')\" routerLink=\"people\">\n          People\n        </a>\n      </li>\n      <li class=\"nav-item\">\n        <a [ngClass]=\"getTabClass('create')\" (click)=\"select('create')\" routerLink=\"create\">\n          Create\n        </a>\n      </li>\n    </ul>\n  </div>\n</nav>\n"

/***/ }),

/***/ "./src/app/dashboard/sidenav/sidenav.component.ts":
/*!********************************************************!*\
  !*** ./src/app/dashboard/sidenav/sidenav.component.ts ***!
  \********************************************************/
/*! exports provided: SidenavComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SidenavComponent", function() { return SidenavComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var SidenavComponent = /** @class */ (function () {
    function SidenavComponent() {
        this.selected = 'companies';
    }
    SidenavComponent.prototype.isSelected = function (tab) {
        return tab === this.selected;
    };
    SidenavComponent.prototype.select = function (tab) {
        this.selected = tab;
    };
    SidenavComponent.prototype.getTabClass = function (tab) {
        if (this.isSelected(tab)) {
            return 'nav-link active';
        }
        else {
            return 'nav-link';
        }
    };
    SidenavComponent.prototype.ngOnInit = function () {
    };
    SidenavComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-sidenav',
            template: __webpack_require__(/*! ./sidenav.component.html */ "./src/app/dashboard/sidenav/sidenav.component.html"),
            styles: [__webpack_require__(/*! ./sidenav.component.css */ "./src/app/dashboard/sidenav/sidenav.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], SidenavComponent);
    return SidenavComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\Samer\Desktop\admin-front\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map