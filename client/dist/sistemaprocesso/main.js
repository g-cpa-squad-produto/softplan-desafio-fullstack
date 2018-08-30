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
		var e = new Error('Cannot find module "' + req + '".');
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./components/user/search.user/search.user.component */ "./src/app/components/user/search.user/search.user.component.ts");
/* harmony import */ var _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./components/user/form.user/form.user.component */ "./src/app/components/user/form.user/form.user.component.ts");
/* harmony import */ var _components_process_search_process_search_process_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./components/process/search.process/search.process.component */ "./src/app/components/process/search.process/search.process.component.ts");
/* harmony import */ var _components_process_form_process_form_process_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./components/process/form.process/form.process.component */ "./src/app/components/process/form.process/form.process.component.ts");
/* harmony import */ var _components_legal_advice_search_legal_advice_search_legal_advice_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./components/legal.advice/search.legal.advice/search.legal.advice.component */ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.ts");
/* harmony import */ var _components_legal_advice_form_legal_advice_form_legal_advice_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./components/legal.advice/form.legal.advice/form.legal.advice.component */ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var appRoutes = [
    {
        path: '',
        // component: LoginComponent
        component: _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_2__["SearchUserComponent"] //OnlyForDevComponent
    },
    {
        path: 'user',
        component: _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_2__["SearchUserComponent"]
    },
    {
        path: 'user/form',
        component: _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_3__["FormUserComponent"]
    },
    {
        path: 'user/form/:id',
        component: _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_3__["FormUserComponent"]
    },
    {
        path: 'process',
        component: _components_process_search_process_search_process_component__WEBPACK_IMPORTED_MODULE_4__["SearchProcessComponent"]
    },
    {
        path: 'process/form',
        component: _components_process_form_process_form_process_component__WEBPACK_IMPORTED_MODULE_5__["FormProcessComponent"]
    },
    {
        path: 'process/form/:id',
        component: _components_process_form_process_form_process_component__WEBPACK_IMPORTED_MODULE_5__["FormProcessComponent"]
    },
    {
        path: 'legalAdvice',
        component: _components_legal_advice_search_legal_advice_search_legal_advice_component__WEBPACK_IMPORTED_MODULE_6__["SearchLegalAdviceComponent"]
    },
    {
        path: 'legalAdvice/form',
        component: _components_legal_advice_form_legal_advice_form_legal_advice_component__WEBPACK_IMPORTED_MODULE_7__["FormLegalAdviceComponent"]
    },
    {
        path: 'legalAdvice/form/:id',
        component: _components_legal_advice_form_legal_advice_form_legal_advice_component__WEBPACK_IMPORTED_MODULE_7__["FormLegalAdviceComponent"]
    }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(appRoutes)
            ],
            exports: [
                _angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]
            ],
            providers: []
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-sidenav-container>\n  <mat-sidenav  #sidenav role=\"navigation\">\n   <mat-nav-list>\n    <a mat-list-item\n        routerLink=\"/user\" (click)=\"sidenav.close()\">\n      <mat-icon class=\"icon\">person</mat-icon>  \n      &nbsp;<span class=\"label\">Usuário</span>\n    </a>\n    <a mat-list-item\n      routerLink=\"/process\" (click)=\"sidenav.close()\">\n      <mat-icon class=\"icon\">dashboard</mat-icon>  \n      &nbsp;<span class=\"label\">Processo</span>\n    </a>\n    </mat-nav-list>\n  </mat-sidenav>\n  <mat-sidenav-content>\n    <mat-toolbar color=\"primary\">\n     <div fxHide.gt-xs>\n       <button mat-icon-button (click)=\"sidenav.toggle()\">\n        <mat-icon>menu</mat-icon>\n      </button>\n    </div>\n     <div>\n       <a routerLink=\"/\">\n          Sistema de Processos\n       </a>\n     </div>\n     <div fxFlex fxLayout fxLayoutAlign=\"flex-end\"  fxHide.xs>\n        <ul fxLayout fxLayoutGap=\"20px\" class=\"navigation-items\">\n            <li>\n              <a\n                routerLink=\"/\">\n                  <mat-icon class=\"icon\">person</mat-icon>\n                  &nbsp;<span class=\"label\">Usuário</span>\n              </a>\n            </li>\n            <li>\n                <a\n                  routerLink=\"/process\">\n                    <mat-icon class=\"icon\">dashboard</mat-icon>\n                    &nbsp;<span class=\"label\">Processo</span>\n                </a>\n              </li>\n        </ul>\n     </div>\n    </mat-toolbar>\n    <main>\n      <router-outlet #o=\"outlet\"></router-outlet>\n    </main>\n  </mat-sidenav-content>\n</mat-sidenav-container>"

/***/ }),

/***/ "./src/app/app.component.scss":
/*!************************************!*\
  !*** ./src/app/app.component.scss ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "mat-sidenav-container, mat-sidenav-content, mat-sidenav {\n  height: 100%; }\n\nmat-sidenav {\n  width: 250px; }\n\na {\n  text-decoration: none;\n  color: white; }\n\na:hover,\na:active {\n  color: lightgray; }\n\n.navigation-items {\n  list-style: none;\n  padding: 0;\n  margin: 0;\n  cursor: pointer; }\n\n.icon {\n  display: inline-block;\n  height: 30px;\n  margin: 0 auto;\n  padding-right: 5px;\n  text-align: center;\n  vertical-align: middle;\n  width: 15%; }\n\n.label {\n  display: inline-block;\n  line-height: 30px;\n  margin: 0;\n  width: 85%; }\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: routerTransition, AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routerTransition", function() { return routerTransition; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_animations__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/animations */ "./node_modules/@angular/animations/fesm5/animations.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var routerTransition = Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["trigger"])('routerTransition', [
    Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["transition"])('* <=> *', [
        // Initial state of new route
        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["query"])(':enter', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            position: 'fixed',
            width: '100%',
            transform: 'translateX(100%)',
            opacity: 1
        }), { optional: true }),
        // move page off screen right on leave
        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["query"])(':leave', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])('300ms ease-out', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            position: 'fixed',
            width: '100%',
            transform: 'translateX(-100%)',
            opacity: 1
        })), { optional: true }),
        // move page in screen from left to right
        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["query"])(':enter', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])('300ms ease-in', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            opacity: 1,
            transform: 'translateX(0%)'
        })), { optional: true }),
    ])
]);
var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'Titulo';
        this.loading = false;
    }
    AppComponent.prototype.ngOnInit = function () {
    };
    AppComponent.prototype.voltar = function () {
        window.history.back();
    };
    AppComponent.prototype.getState = function (outlet) {
        return outlet.activatedRouteData.state;
    };
    AppComponent.prototype.ngOnDestroy = function () {
        this.loaderSubscription.unsubscribe();
    };
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.scss */ "./src/app/app.component.scss")],
            animations: [routerTransition],
        }),
        __metadata("design:paramtypes", [])
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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_cdk_table__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/cdk/table */ "./node_modules/@angular/cdk/esm5/table.es5.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _components_imageviewer_imageviewer_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./components/imageviewer/imageviewer.component */ "./src/app/components/imageviewer/imageviewer.component.ts");
/* harmony import */ var _hallysonh_ngx_imageviewer__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @hallysonh/ngx-imageviewer */ "./node_modules/@hallysonh/ngx-imageviewer/fesm5/hallysonh-ngx-imageviewer.js");
/* harmony import */ var _components_custom_toolbar_custom_toolbar_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./components/custom-toolbar/custom.toolbar.component */ "./src/app/components/custom-toolbar/custom.toolbar.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _components_login_login_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./components/login/login.component */ "./src/app/components/login/login.component.ts");
/* harmony import */ var _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./components/user/search.user/search.user.component */ "./src/app/components/user/search.user/search.user.component.ts");
/* harmony import */ var _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./components/user/form.user/form.user.component */ "./src/app/components/user/form.user/form.user.component.ts");
/* harmony import */ var _components_process_search_process_search_process_component__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./components/process/search.process/search.process.component */ "./src/app/components/process/search.process/search.process.component.ts");
/* harmony import */ var _components_process_form_process_form_process_component__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./components/process/form.process/form.process.component */ "./src/app/components/process/form.process/form.process.component.ts");
/* harmony import */ var _components_legal_advice_search_legal_advice_search_legal_advice_component__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./components/legal.advice/search.legal.advice/search.legal.advice.component */ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.ts");
/* harmony import */ var _components_legal_advice_form_legal_advice_form_legal_advice_component__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./components/legal.advice/form.legal.advice/form.legal.advice.component */ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
// Core Modules






// teste zoom image on IE



// Material UI


// Rotas

// Components








var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_12__["AppComponent"],
                _components_login_login_component__WEBPACK_IMPORTED_MODULE_13__["LoginComponent"],
                _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_14__["SearchUserComponent"],
                _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_15__["FormUserComponent"],
                _components_process_search_process_search_process_component__WEBPACK_IMPORTED_MODULE_16__["SearchProcessComponent"],
                _components_process_form_process_form_process_component__WEBPACK_IMPORTED_MODULE_17__["FormProcessComponent"],
                _components_legal_advice_search_legal_advice_search_legal_advice_component__WEBPACK_IMPORTED_MODULE_18__["SearchLegalAdviceComponent"],
                _components_legal_advice_form_legal_advice_form_legal_advice_component__WEBPACK_IMPORTED_MODULE_19__["FormLegalAdviceComponent"],
                _components_imageviewer_imageviewer_component__WEBPACK_IMPORTED_MODULE_6__["ImageViewerComponent"],
                _components_custom_toolbar_custom_toolbar_component__WEBPACK_IMPORTED_MODULE_8__["CustomToolBarComponent"]
            ],
            imports: [
                // ##CORE
                _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClientModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__["BrowserAnimationsModule"],
                _angular_cdk_table__WEBPACK_IMPORTED_MODULE_4__["CdkTableModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
                // ##MATERIAL
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatDividerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatChipsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatProgressBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatProgressSpinnerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatGridListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatAutocompleteModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatStepperModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatSidenavModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatBadgeModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_9__["MatDialogModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_10__["FlexLayoutModule"],
                // ##MATERIAL
                // ##IMAGE VIEWER
                _hallysonh_ngx_imageviewer__WEBPACK_IMPORTED_MODULE_7__["ImageViewerModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_11__["AppRoutingModule"] // ROTAS
            ],
            bootstrap: [
                _app_component__WEBPACK_IMPORTED_MODULE_12__["AppComponent"]
            ]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/components/custom-toolbar/custom.toolbar.component.css":
/*!************************************************************************!*\
  !*** ./src/app/components/custom-toolbar/custom.toolbar.component.css ***!
  \************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "mat-sidenav-container, mat-sidenav-content, mat-sidenav {\n    height: 100%;\n  }\n  \n  mat-sidenav {\n    width: 250px;\n  }\n  \n  a {\n    text-decoration: none;\n    color: white;\n  }\n  \n  a:hover,\n  a:active {\n    color: lightgray;\n  }\n  \n  .navigation-items {\n    list-style: none;\n    padding: 0;\n    margin: 0;\n    cursor: pointer;\n  }\n  \n  .icon {\n    display: inline-block;\n    height: 30px;\n    margin: 0 auto;\n    padding-right: 5px;\n    text-align: center;\n    vertical-align: middle;\n    width: 15%;\n  }\n  \n  .label {\n    display: inline-block;\n    line-height: 30px;\n    margin: 0;\n    width: 85%;\n  }"

/***/ }),

/***/ "./src/app/components/custom-toolbar/custom.toolbar.component.html":
/*!*************************************************************************!*\
  !*** ./src/app/components/custom-toolbar/custom.toolbar.component.html ***!
  \*************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-sidenav-container>\n  <mat-sidenav  #sidenav role=\"navigation\">\n   <mat-nav-list>\n    <a mat-list-item>\n      <mat-icon class=\"icon\">dashboard</mat-icon>&nbsp;\n      <span class=\"label\">Processo</span>\n    </a>\n    <a  mat-list-item type=\"button\" (click)=\"routeFormUser()\">\n      <mat-icon class=\"icon\">person</mat-icon>&nbsp;\n      <span class=\"label\">Usuário</span>\n    </a>  \n    </mat-nav-list>\n  </mat-sidenav>\n  <mat-sidenav-content>\n    <mat-toolbar color=\"primary\">\n     <div fxHide.gt-xs>\n       <button mat-icon-button>\n        <mat-icon>menu</mat-icon>\n      </button>\n    </div>\n     <div>\n       <a>\n          Sistema de Processo\n       </a>\n     </div>\n     <div fxFlex fxLayout fxLayoutAlign=\"flex-end\"  fxHide.xs>\n        <ul fxLayout fxLayoutGap=\"20px\" class=\"navigation-items\">\n            <li>\n              <a (click)=\"routeFormUser()\">\n                  <mat-icon class=\"icon\">person</mat-icon>&nbsp;\n                  <span class=\"label\">Usuário</span>\n              </a>\n            </li>\n            <li>\n                <a>\n                    <mat-icon class=\"icon\">dashboard</mat-icon>&nbsp;\n                    <span class=\"label\">Processo</span>\n                </a>\n              </li>\n        </ul>\n     </div>\n    </mat-toolbar>\n    <main>\n    </main>\n  </mat-sidenav-content>\n</mat-sidenav-container>"

/***/ }),

/***/ "./src/app/components/custom-toolbar/custom.toolbar.component.ts":
/*!***********************************************************************!*\
  !*** ./src/app/components/custom-toolbar/custom.toolbar.component.ts ***!
  \***********************************************************************/
/*! exports provided: CustomToolBarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomToolBarComponent", function() { return CustomToolBarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var CustomToolBarComponent = /** @class */ (function () {
    function CustomToolBarComponent(router) {
        this.router = router;
    }
    CustomToolBarComponent.prototype.routeFormUser = function () {
        this.router.navigate(["/user"]);
    };
    CustomToolBarComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-custom-tool-bar',
            template: __webpack_require__(/*! ./custom.toolbar.component.html */ "./src/app/components/custom-toolbar/custom.toolbar.component.html"),
            styles: [__webpack_require__(/*! ./custom.toolbar.component.css */ "./src/app/components/custom-toolbar/custom.toolbar.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], CustomToolBarComponent);
    return CustomToolBarComponent;
}());



/***/ }),

/***/ "./src/app/components/imageviewer/imageviewer.component.html":
/*!*******************************************************************!*\
  !*** ./src/app/components/imageviewer/imageviewer.component.html ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <div class=\"imagecontainer\">\n    <div #imagewrapper class=\"imagewrapper\">\n      <ngx-imageviewer src=\"{{ urlSrc }}\" [width]=\"canvasDim.width\" [height]=\"canvasDim.height\"></ngx-imageviewer>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/components/imageviewer/imageviewer.component.scss":
/*!*******************************************************************!*\
  !*** ./src/app/components/imageviewer/imageviewer.component.scss ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ":host {\n  display: flex;\n  flex-direction: column;\n  height: 100%; }\n\nh2 {\n  margin-left: 20px;\n  white-space: nowrap; }\n\n.imagewrapper {\n  flex: 1;\n  box-sizing: border-box;\n  overflow: hidden;\n  max-width: calc(100vw -  240px);\n  min-width: calc(100vw - 240px);\n  width: calc(100vw - 240px);\n  max-height: calc(100vh - 152px);\n  min-height: calc(100vh - 152px);\n  height: calc(100vh - 152px); }\n\n@media only screen and (max-width: 600px) {\n    .imagewrapper {\n      max-width: calc(100vw - 40px);\n      min-width: calc(100vw - 40px);\n      width: calc(100vw - 40px); } }\n"

/***/ }),

/***/ "./src/app/components/imageviewer/imageviewer.component.ts":
/*!*****************************************************************!*\
  !*** ./src/app/components/imageviewer/imageviewer.component.ts ***!
  \*****************************************************************/
/*! exports provided: ImageViewerComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ImageViewerComponent", function() { return ImageViewerComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _hallysonh_ngx_imageviewer__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @hallysonh/ngx-imageviewer */ "./node_modules/@hallysonh/ngx-imageviewer/fesm5/hallysonh-ngx-imageviewer.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var IMAGEVIEWER_CUSTOM_CONFIG = {
    width: 200,
    height: 200,
    bgStyle: '#ffffff',
    buttonStyle: {
        bgStyle: '#80CBC4',
        iconStyle: '#ffffff',
        borderStyle: '#ffffff',
        borderWidth: 2 // buttons' border width (0 == disabled)
    }
};
var ImageViewerComponent = /** @class */ (function () {
    function ImageViewerComponent() {
        this._canvasDim = { width: 10, height: 10 };
    }
    Object.defineProperty(ImageViewerComponent.prototype, "canvasDim", {
        get: function () {
            return this._canvasDim;
        },
        enumerable: true,
        configurable: true
    });
    ImageViewerComponent.prototype.ngAfterViewInit = function () {
        this.updateCanvasDim();
    };
    ImageViewerComponent.prototype.onResize = function (event) {
        this.updateCanvasDim();
    };
    ImageViewerComponent.prototype.updateCanvasDim = function () {
        var _this = this;
        var el = this.wrapper && this.wrapper.nativeElement ? this.wrapper.nativeElement : null;
        if (el && (el.offsetWidth !== this._canvasDim.width || el.offsetHeight !== this._canvasDim.height)) {
            var newDim_1 = { width: el.offsetWidth - 2, height: el.offsetHeight - 2 };
            setTimeout(function () { return _this._canvasDim = newDim_1; }, 0);
        }
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('imagewrapper'),
        __metadata("design:type", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"])
    ], ImageViewerComponent.prototype, "wrapper", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", String)
    ], ImageViewerComponent.prototype, "urlSrc", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('window:resize', ['$event']),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", [Object]),
        __metadata("design:returntype", void 0)
    ], ImageViewerComponent.prototype, "onResize", null);
    ImageViewerComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-imageviewer',
            template: __webpack_require__(/*! ./imageviewer.component.html */ "./src/app/components/imageviewer/imageviewer.component.html"),
            styles: [__webpack_require__(/*! ./imageviewer.component.scss */ "./src/app/components/imageviewer/imageviewer.component.scss")],
            providers: [
                {
                    provide: _hallysonh_ngx_imageviewer__WEBPACK_IMPORTED_MODULE_1__["IMAGEVIEWER_CONFIG"],
                    useValue: IMAGEVIEWER_CUSTOM_CONFIG
                }
            ]
        })
    ], ImageViewerComponent);
    return ImageViewerComponent;
}());



/***/ }),

/***/ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.html":
/*!********************************************************************************************!*\
  !*** ./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.html ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-content >\n        <mat-card-title >\n          <button mat-icon-button color=\"accent\" (click)=\"voltar()\"><mat-icon>keyboard_backspace</mat-icon> </button>\n          &nbsp;Parecer Jurídico\n        </mat-card-title>\n    </mat-card-content>\n\n    <mat-card-content>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Descrição\" id=\"description\" [(ngModel)]=\"legalAdvice.description\" required>\n        </mat-form-field>\n      </div>\n      \n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"legalAdvice.idFinishedBy\"> \n          <div style=\"color: red\"> \n              <mat-icon>info</mat-icon>Parecer Finalizado!\n          </div>\n    </div>\n      \n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"legalAdvice.id && !legalAdvice.idFinishedBy\">\n              <button mat-button> \n                  <mat-icon>info</mat-icon> Finalizar Parecer\n              </button>\n      </div>\n\n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"legalAdvice.id && !legalAdvice.idFinishedBy\">\n          <button mat-button (click)=\"save()\"> \n              <mat-icon>save</mat-icon> Salvar\n          </button>\n      </div>\n\n\n      <!--\n      <div fxLayout=\"row\" fxLayout=\"column\">\n          <mat-form-field >\n              <input matInput type=\"text\" \n                        placeholder=\"Finalizador\"  \n                        [matAutocomplete]=\"auto\" \n                        [(ngModel)]=\"user\" \n                        name=\"user\" required>\n              <mat-autocomplete #auto=\"matAutocomplete\">\n                  <mat-option *ngFor=\"let u of users\" [value]=\"u\">\n                      {{u}}\n                  </mat-option>\n\n                    <mat-error *ngIf=\"description\">\n                      Campo obrigatório\n                    </mat-error>\n              </mat-autocomplete>\n          </mat-form-field>\n        </div>\n      -->\n    <!--\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\" *ngIf=\"!legalAdvice.idFinishedBy\">\n        <span class=\"send\">\n          <button mat-button (click)=\"save()\"> \n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    -->\n    </mat-card-content>\n\n  </mat-card>\n</div>\n"

/***/ }),

/***/ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.scss":
/*!********************************************************************************************!*\
  !*** ./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.scss ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.ts":
/*!******************************************************************************************!*\
  !*** ./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.ts ***!
  \******************************************************************************************/
/*! exports provided: FormLegalAdviceComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FormLegalAdviceComponent", function() { return FormLegalAdviceComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_legal_advice_legal_advice_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/legal.advice/legal.advice.service */ "./src/app/services/legal.advice/legal.advice.service.ts");
/* harmony import */ var _services_legal_advice_legal_advice_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../services/legal.advice/legal.advice.model */ "./src/app/services/legal.advice/legal.advice.model.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var FormLegalAdviceComponent = /** @class */ (function () {
    function FormLegalAdviceComponent(route, router, legalAdviceService) {
        this.route = route;
        this.router = router;
        this.legalAdviceService = legalAdviceService;
        this.legalAdvice = new _services_legal_advice_legal_advice_model__WEBPACK_IMPORTED_MODULE_3__["LegalAdvice"]();
        this.users = ['asdasd', 'asdfasd', 'bhjfj', 'sdf sthd', 'asdasd', 'asdfasd', 'bhjfj', 'sdf sthd'];
    }
    FormLegalAdviceComponent.prototype.ngOnInit = function () {
        /*
        this.route.paramMap.subscribe(params => {
          if(params.get('id')){
            this.legalAdviceId = Number(params.get('id'))
            this.legalAdviceService.getLegalAdviceById(this.legalAdviceId).subscribe(
              l =>{
                  this.legalAdvice = l;
              }
            )
          }else{
            this.legalAdvice = new LegalAdvice();
          }
        });
        */
    };
    FormLegalAdviceComponent.prototype.save = function () {
        if (this.legalAdvice.id) {
            this.upDateProcess();
        }
        else {
            this.createProcess();
        }
    };
    FormLegalAdviceComponent.prototype.createProcess = function () {
        var _this = this;
        this.legalAdvice.idCreatedBy = 9999991;
        this.legalAdviceService.createLegalAdvice(this.legalAdvice).subscribe(function (u) {
            _this.router.navigate(['/legalAdvice']);
        }, function (err) {
            console.error(err);
        });
    };
    FormLegalAdviceComponent.prototype.upDateProcess = function () {
        var _this = this;
        this.legalAdviceService.updateLegalAdvice(this.legalAdviceId, this.legalAdvice).subscribe(function (p) {
            _this.router.navigate(['/legalAdvice']);
        }, function (err) {
            console.error(err);
        });
    };
    FormLegalAdviceComponent.prototype.voltar = function () {
        window.history.back();
    };
    FormLegalAdviceComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-form-legal-advice',
            template: __webpack_require__(/*! ./form.legal.advice.component.html */ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.html"),
            styles: [__webpack_require__(/*! ./form.legal.advice.component.scss */ "./src/app/components/legal.advice/form.legal.advice/form.legal.advice.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _services_legal_advice_legal_advice_service__WEBPACK_IMPORTED_MODULE_2__["LegalAdviceService"]])
    ], FormLegalAdviceComponent);
    return FormLegalAdviceComponent;
}());



/***/ }),

/***/ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.html":
/*!************************************************************************************************!*\
  !*** ./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.html ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-title>\n        <button mat-icon-button color=\"accent\" >\n            <mat-icon>dashboard</mat-icon>\n            Adicionar Novo\n        </button>\n    </mat-card-title>\n\n    <mat-card-content>\n      <div *ngIf=\"isError\">\n        <mat-chip-list >\n          <mat-chip style=\"margin-left: 30%\" color=\"warn\" selected=\"true\"><mat-chip-avatar> <mat-icon>error</mat-icon></mat-chip-avatar> {{ error }}</mat-chip>\n        </mat-chip-list>\n      </div>\n    </mat-card-content>\n\n    <mat-card-content>\n      <mat-list-item *ngFor=\"let l of legalAdvices\">\n        <mat-divider [inset]=\"true\"></mat-divider>\n        <h4 mat-line  ><mat-icon>dashboard</mat-icon> {{l.description}} </h4>\n        <mat-divider inset *ngIf=\"!last\"></mat-divider>\n      </mat-list-item>\n    </mat-card-content>\n  </mat-card>\n</div>"

/***/ }),

/***/ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.scss":
/*!************************************************************************************************!*\
  !*** ./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.scss ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".demo-list {\n  display: flex;\n  flex-flow: row wrap; }\n  .demo-list .mat-list, .demo-list .mat-nav-list, .demo-list .mat-selection-list {\n    border: 1px solid rgba(0, 0, 0, 0.12);\n    width: 350px;\n    margin: 20px 20px 0 0; }\n  .demo-list h2 {\n    margin-top: 20px; }\n  .demo-list .mat-icon {\n    color: rgba(0, 0, 0, 0.12); }\n  .demo-list .mat-list-icon {\n    color: white;\n    background: rgba(0, 0, 0, 0.3); }\n  .demo-secondary-text {\n  color: rgba(0, 0, 0, 0.54); }\n"

/***/ }),

/***/ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.ts":
/*!**********************************************************************************************!*\
  !*** ./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.ts ***!
  \**********************************************************************************************/
/*! exports provided: SearchLegalAdviceComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchLegalAdviceComponent", function() { return SearchLegalAdviceComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_legal_advice_legal_advice_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/legal.advice/legal.advice.service */ "./src/app/services/legal.advice/legal.advice.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SearchLegalAdviceComponent = /** @class */ (function () {
    function SearchLegalAdviceComponent(legalAdviceService, router) {
        this.legalAdviceService = legalAdviceService;
        this.router = router;
        // exibicao de mensagem de erro na tela
        this.isError = false;
        // exibicao de loader na tela
        this.exibeProgress = false;
        this.value = 0;
    }
    SearchLegalAdviceComponent.prototype.routeFormLegalAdvice = function (id) {
        this.router.navigate(["/legalAdvice/form/" + id]);
    };
    SearchLegalAdviceComponent.prototype.routeFormNewLegalAdvice = function () {
        this.router.navigate(["/legalAdvice/form"]);
    };
    SearchLegalAdviceComponent.prototype.back = function () {
        window.history.back();
    };
    SearchLegalAdviceComponent.prototype.ngOnInit = function () {
        /*
        this.legalAdviceService.getAllLegalAdvices()
          .subscribe(res => {
            console.log('>>>> get legal advices res=', res);
            this.legalAdvices = res;
            this.exibeProgress = false;
        },
        error => {
          console.log('error service get legal advices ==>', error);
          this.exibeProgress = false;
          this.isError = true;
          this.error = `Não há legal advices para ser exibido`;
        });
        */
    };
    SearchLegalAdviceComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-search-legal-advice-component',
            template: __webpack_require__(/*! ./search.legal.advice.component.html */ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.html"),
            styles: [__webpack_require__(/*! ./search.legal.advice.component.scss */ "./src/app/components/legal.advice/search.legal.advice/search.legal.advice.component.scss")]
        }),
        __metadata("design:paramtypes", [_services_legal_advice_legal_advice_service__WEBPACK_IMPORTED_MODULE_2__["LegalAdviceService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], SearchLegalAdviceComponent);
    return SearchLegalAdviceComponent;
}());



/***/ }),

/***/ "./src/app/components/login/login.component.css":
/*!******************************************************!*\
  !*** ./src/app/components/login/login.component.css ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/components/login/login.component.html":
/*!*******************************************************!*\
  !*** ./src/app/components/login/login.component.html ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "  <div>\n  <mat-card>\n    <mat-card-title><mat-card-title><mat-icon>lock</mat-icon> Login  </mat-card-title></mat-card-title>\n\n    <mat-card-content>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"User\" id=\"user\" [(ngModel)]=\"user\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Password\" type=\"password\" id=\"password\" [(ngModel)]=\"password\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"loginError\">\n        <mat-chip-list>\n          <mat-chip selected=\"true\" color=\"warn\">\n            <mat-icon>warning</mat-icon>\n            {{ loginError }}\n          </mat-chip>\n        </mat-chip-list>\n      </div>\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\">\n        <span class=\"send\">\n          <button mat-fab type=\"submit\" id=\"login\" >\n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    </mat-card-content>\n\n  </mat-card>\n</div>\n\n"

/***/ }),

/***/ "./src/app/components/login/login.component.ts":
/*!*****************************************************!*\
  !*** ./src/app/components/login/login.component.ts ***!
  \*****************************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __assign = (undefined && undefined.__assign) || Object.assign || function(t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
};
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var LoginComponent = /** @class */ (function () {
    function LoginComponent(router) {
        this.router = router;
    }
    LoginComponent.prototype.login = function () {
        console.log('efetuando login');
        this.router.navigate(['/user']);
    };
    LoginComponent.prototype.reduceParaAgrupar = function () {
        var guias = [
            { peg_id: '1', guia_id: 'guia1', instance: '2342342' },
            { peg_id: '2', guia_id: 'guia22', instance: '254524423' },
            { peg_id: '1', guia_id: 'guia11', instance: '6967853' },
            { peg_id: '2', guia_id: 'guia2', instance: '4678643' },
            { peg_id: '1', guia_id: 'guia111', instance: '9824612' }
        ];
        var novoArray = guias
            .reduce(function (acumulador, atual) {
            acumulador[atual.peg_id] = acumulador[atual.peg_id] || [];
            acumulador[atual.peg_id].push(atual);
            return acumulador;
        }, Object.create(null));
        console.log(novoArray);
    };
    LoginComponent.prototype.reduceParaProcurar = function () {
        var tasks = [
            { name: 'nome', id: '1', processInstance: { id: '6574534' }, taskDefinitionKey: '2342342' },
            { name: 'nome', id: '1', processInstance: { id: '6574534' }, taskDefinitionKey: '2342342' },
            { name: 'nome', id: '1', processInstance: { id: '6574534' }, taskDefinitionKey: '2342342' },
            { name: 'nome', id: '1', processInstance: { id: '6574534' }, taskDefinitionKey: '2342342' }
        ];
        var novoArray = tasks
            .filter(function (d) { return d.processInstance.id; })
            .reduce(function (reduce, task) {
            var current = reduce.find(function (rd) { return rd.taskDefinitionKey === task.taskDefinitionKey; });
            if (current) {
                current.processInstances.push(__assign({ taskId: task.id }, task.processInstance));
            }
            else {
                reduce.push({
                    name: task.name,
                    taskDefinitionKey: task.taskDefinitionKey,
                    processInstances: [__assign({ taskId: task.id }, task.processInstance)]
                });
            }
            return reduce;
        }, []);
        console.log(novoArray);
    };
    LoginComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.component.html */ "./src/app/components/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.css */ "./src/app/components/login/login.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/components/process/form.process/form.process.component.html":
/*!*****************************************************************************!*\
  !*** ./src/app/components/process/form.process/form.process.component.html ***!
  \*****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-content >\n        <mat-card-title >\n          <button mat-icon-button color=\"accent\" (click)=\"voltar()\"><mat-icon>keyboard_backspace</mat-icon> </button>\n          &nbsp;Processo\n        </mat-card-title>\n    </mat-card-content>\n\n    <mat-card-content>\n        <div fxLayout=\"row\" fxLayout=\"column\">\n            <mat-form-field>\n              <input matInput placeholder=\"Número\" id=\"code\" [(ngModel)]=\"process.code\" required>\n            </mat-form-field>\n        </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Resumo\" id=\"summary\" [(ngModel)]=\"process.summary\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Descrição\" id=\"description\" [(ngModel)]=\"process.description\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n          <mat-card-content>\n              <mat-list-item *ngFor=\"let l of listLegalAdvice\">\n                <mat-divider [inset]=\"true\"></mat-divider>\n                <h4 mat-line ><mat-icon>dashboard</mat-icon> {{l.loginResponsableFor}}  </h4>\n                <mat-divider inset *ngIf=\"!last\"></mat-divider>\n              </mat-list-item>\n          </mat-card-content>\n          <mat-card-content>\n              <div fxLayout=\"row\" fxLayoutAlign=\"center\">\n                <mat-select style=\"margin-top: 30px\" placeholder=\"Usuário Responsável pelo Parecer\" [(ngModel)]=\"legalAdvice.idResponsableFor\">\n                  <mat-option *ngFor=\"let u of users\" [value]=\"u.id\">\n                    {{u.login}}\n                </mat-option>\n                </mat-select>\n                <button style=\"margin-top: 30px\" mat-button color=\"accent\" fxLayoutAlign=\"end\"  (click)=\"addLegalAdvice()\"> \n                Incluir \n                </button>\n              </div>\n          </mat-card-content>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"process.idFinishedBy\"> \n          <div style=\"color: red\"> \n              <mat-icon>info</mat-icon>Processo Finalizado!\n          </div>\n    </div>\n      \n      <div style=\"margin-top: 30px\" fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"process.id && !process.idFinishedBy\">\n              <button mat-button color=\"warn\"> \n                  <mat-icon>info</mat-icon> Finalizar Processo\n              </button>\n      </div>\n\n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"process.id && !process.idFinishedBy\">\n          <button mat-button color=\"accent\" (click)=\"save()\"> \n              <mat-icon>save</mat-icon> Salvar\n          </button>\n      </div>\n\n\n      <!--\n      <div fxLayout=\"row\" fxLayout=\"column\">\n          <mat-form-field >\n              <input matInput type=\"text\" \n                        placeholder=\"Finalizador\"  \n                        [matAutocomplete]=\"auto\" \n                        [(ngModel)]=\"user\" \n                        name=\"user\" required>\n              <mat-autocomplete #auto=\"matAutocomplete\">\n                  <mat-option *ngFor=\"let u of users\" [value]=\"u\">\n                      {{u}}\n                  </mat-option>\n\n                    <mat-error *ngIf=\"description\">\n                      Campo obrigatório\n                    </mat-error>\n              </mat-autocomplete>\n          </mat-form-field>\n        </div>\n      -->\n    <!--\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\" *ngIf=\"!process.idFinishedBy\">\n        <span class=\"send\">\n          <button mat-button (click)=\"save()\"> \n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    -->\n    </mat-card-content>\n\n  </mat-card>\n</div>\n"

/***/ }),

/***/ "./src/app/components/process/form.process/form.process.component.scss":
/*!*****************************************************************************!*\
  !*** ./src/app/components/process/form.process/form.process.component.scss ***!
  \*****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/components/process/form.process/form.process.component.ts":
/*!***************************************************************************!*\
  !*** ./src/app/components/process/form.process/form.process.component.ts ***!
  \***************************************************************************/
/*! exports provided: FormProcessComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FormProcessComponent", function() { return FormProcessComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_process_process_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/process/process.service */ "./src/app/services/process/process.service.ts");
/* harmony import */ var _services_process_process_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../services/process/process.model */ "./src/app/services/process/process.model.ts");
/* harmony import */ var _services_legal_advice_legal_advice_model__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../../services/legal.advice/legal.advice.model */ "./src/app/services/legal.advice/legal.advice.model.ts");
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../../services/user/user.service */ "./src/app/services/user/user.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var FormProcessComponent = /** @class */ (function () {
    function FormProcessComponent(route, router, processService, userService) {
        this.route = route;
        this.router = router;
        this.processService = processService;
        this.userService = userService;
        this.process = new _services_process_process_model__WEBPACK_IMPORTED_MODULE_3__["Process"]();
        this.legalAdvice = new _services_legal_advice_legal_advice_model__WEBPACK_IMPORTED_MODULE_4__["LegalAdvice"]();
        this.listLegalAdvice = new Array();
    }
    FormProcessComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.paramMap.subscribe(function (params) {
            if (params.get('id')) {
                _this.processId = Number(params.get('id'));
                _this.processService.getProcessById(_this.processId).subscribe(function (p) {
                    _this.process = p;
                });
            }
            else {
                _this.process = new _services_process_process_model__WEBPACK_IMPORTED_MODULE_3__["Process"]();
            }
        });
        this.userService.getAllUsers()
            .subscribe(function (res) {
            console.log('>>>> get users res=', res);
            _this.users = res;
        }, function (error) {
            console.log('error service get users ==>', error);
        });
    };
    FormProcessComponent.prototype.addLegalAdvice = function () {
        var _this = this;
        var tempUser = this.users.find(function (l) { return l.id == _this.legalAdvice.idResponsableFor; });
        console.log('tempUser', tempUser);
        this.legalAdvice.loginResponsableFor = tempUser.login;
        var newListLegalAdvice = this.listLegalAdvice.slice(0);
        newListLegalAdvice.push(this.legalAdvice);
        this.listLegalAdvice = newListLegalAdvice;
        this.legalAdvice = new _services_legal_advice_legal_advice_model__WEBPACK_IMPORTED_MODULE_4__["LegalAdvice"]();
    };
    FormProcessComponent.prototype.save = function () {
        if (this.process.id) {
            this.upDateProcess();
        }
        else {
            this.createProcess();
        }
    };
    FormProcessComponent.prototype.createProcess = function () {
        var _this = this;
        this.process.idCreatedBy = 9999991;
        this.process.legalAdvices = this.listLegalAdvice;
        this.processService.createProcess(this.process).subscribe(function (u) {
            _this.router.navigate(['/process']);
        }, function (err) {
            console.error(err);
        });
    };
    FormProcessComponent.prototype.upDateProcess = function () {
        var _this = this;
        this.processService.updateProcess(this.processId, this.process).subscribe(function (p) {
            _this.router.navigate(['/process']);
        }, function (err) {
            console.error(err);
        });
    };
    FormProcessComponent.prototype.voltar = function () {
        window.history.back();
    };
    FormProcessComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-form-process',
            template: __webpack_require__(/*! ./form.process.component.html */ "./src/app/components/process/form.process/form.process.component.html"),
            styles: [__webpack_require__(/*! ./form.process.component.scss */ "./src/app/components/process/form.process/form.process.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _services_process_process_service__WEBPACK_IMPORTED_MODULE_2__["ProcessService"],
            _services_user_user_service__WEBPACK_IMPORTED_MODULE_5__["UserService"]])
    ], FormProcessComponent);
    return FormProcessComponent;
}());



/***/ }),

/***/ "./src/app/components/process/search.process/search.process.component.html":
/*!*********************************************************************************!*\
  !*** ./src/app/components/process/search.process/search.process.component.html ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-title>\n        <button mat-icon-button color=\"accent\" (click)=\"routeFormNewProcess()\">\n            <mat-icon>dashboard</mat-icon>\n            Adicionar Novo\n        </button>\n    </mat-card-title>\n\n    <mat-card-content>\n      <div *ngIf=\"isError\">\n        <mat-chip-list >\n          <mat-chip style=\"margin-left: 30%\" color=\"warn\" selected=\"true\"><mat-chip-avatar> <mat-icon>error</mat-icon></mat-chip-avatar> {{ error }}</mat-chip>\n        </mat-chip-list>\n      </div>\n    </mat-card-content>\n\n    <mat-card-content>\n      <mat-list-item *ngFor=\"let p of processes\">\n        <mat-divider [inset]=\"true\"></mat-divider>\n        <h4 mat-line (click)=\"routeFormProcess(p.id)\" ><mat-icon>dashboard</mat-icon> {{p.code}}  |  {{p.summary}} </h4>\n        <mat-divider inset *ngIf=\"!last\"></mat-divider>\n      </mat-list-item>\n    </mat-card-content>\n  </mat-card>\n</div>"

/***/ }),

/***/ "./src/app/components/process/search.process/search.process.component.scss":
/*!*********************************************************************************!*\
  !*** ./src/app/components/process/search.process/search.process.component.scss ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".demo-list {\n  display: flex;\n  flex-flow: row wrap; }\n  .demo-list .mat-list, .demo-list .mat-nav-list, .demo-list .mat-selection-list {\n    border: 1px solid rgba(0, 0, 0, 0.12);\n    width: 350px;\n    margin: 20px 20px 0 0; }\n  .demo-list h2 {\n    margin-top: 20px; }\n  .demo-list .mat-icon {\n    color: rgba(0, 0, 0, 0.12); }\n  .demo-list .mat-list-icon {\n    color: white;\n    background: rgba(0, 0, 0, 0.3); }\n  .demo-secondary-text {\n  color: rgba(0, 0, 0, 0.54); }\n"

/***/ }),

/***/ "./src/app/components/process/search.process/search.process.component.ts":
/*!*******************************************************************************!*\
  !*** ./src/app/components/process/search.process/search.process.component.ts ***!
  \*******************************************************************************/
/*! exports provided: SearchProcessComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchProcessComponent", function() { return SearchProcessComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_process_process_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/process/process.service */ "./src/app/services/process/process.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SearchProcessComponent = /** @class */ (function () {
    function SearchProcessComponent(processService, router) {
        this.processService = processService;
        this.router = router;
        // exibicao de mensagem de erro na tela
        this.isError = false;
        // exibicao de loader na tela
        this.exibeProgress = false;
        this.value = 0;
    }
    SearchProcessComponent.prototype.routeFormProcess = function (id) {
        this.router.navigate(["/process/form/" + id]);
    };
    SearchProcessComponent.prototype.routeFormNewProcess = function () {
        this.router.navigate(["/process/form"]);
    };
    SearchProcessComponent.prototype.back = function () {
        window.history.back();
    };
    SearchProcessComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.processService.getAllProcesses()
            .subscribe(function (res) {
            console.log('>>>> get processes res=', res);
            _this.processes = res;
            _this.exibeProgress = false;
            // this.router.navigate(['/users'])
        }, function (error) {
            console.log('error service get processes ==>', error);
            _this.exibeProgress = false;
            _this.isError = true;
            _this.error = "N\u00E3o h\u00E1 processos para ser exibido";
        });
    };
    SearchProcessComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-search-process-component',
            template: __webpack_require__(/*! ./search.process.component.html */ "./src/app/components/process/search.process/search.process.component.html"),
            styles: [__webpack_require__(/*! ./search.process.component.scss */ "./src/app/components/process/search.process/search.process.component.scss")]
        }),
        __metadata("design:paramtypes", [_services_process_process_service__WEBPACK_IMPORTED_MODULE_2__["ProcessService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], SearchProcessComponent);
    return SearchProcessComponent;
}());



/***/ }),

/***/ "./src/app/components/user/form.user/form.user.component.html":
/*!********************************************************************!*\
  !*** ./src/app/components/user/form.user/form.user.component.html ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-content >\n        <mat-card-title >\n          <button mat-icon-button color=\"accent\" (click)=\"voltar()\"><mat-icon>keyboard_backspace</mat-icon> </button>\n          &nbsp;Usuário\n        </mat-card-title>\n    </mat-card-content>\n\n    <mat-card-content>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Nome\" id=\"name\" [(ngModel)]=\"user.name\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Login\" id=\"login\" [(ngModel)]=\"user.login\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput type=\"password\" placeholder=\"Password\" id=\"password\" [(ngModel)]=\"user.password\" required>\n        </mat-form-field>\n      </div>\n\n      <div fxLayout=\"row\" fxLayout=\"column\">\n          <mat-form-field>\n              <mat-select placeholder=\"Perfil\" [(ngModel)]=\"user.profile\">\n                  <mat-option value=\"ADMINISTRADOR\" >ADMINISTRADOR</mat-option>\n                  <mat-option value=\"TRIADOR\">TRIADOR</mat-option>\n                  <mat-option value=\"FINALIZADOR\">FINALIZADOR</mat-option>\n              </mat-select>\n          </mat-form-field>\n      </div>\n\n\n      <div fxLayout=\"row\" fxLayout=\"column\">\n          <mat-form-field>\n              <mat-select placeholder=\"Status\" [(ngModel)]=\"user.status\">\n                  <mat-option value=\"ATIVO\" >ATIVO</mat-option>\n                  <mat-option value=\"INATIVO\">INATIVO</mat-option>\n              </mat-select>\n          </mat-form-field>\n      </div>\n\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\">\n        <span class=\"send\">\n          <button mat-fab (click)=\"save()\"> \n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    </mat-card-content>\n\n  </mat-card>\n</div>\n"

/***/ }),

/***/ "./src/app/components/user/form.user/form.user.component.scss":
/*!********************************************************************!*\
  !*** ./src/app/components/user/form.user/form.user.component.scss ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/components/user/form.user/form.user.component.ts":
/*!******************************************************************!*\
  !*** ./src/app/components/user/form.user/form.user.component.ts ***!
  \******************************************************************/
/*! exports provided: FormUserComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FormUserComponent", function() { return FormUserComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/user/user.service */ "./src/app/services/user/user.service.ts");
/* harmony import */ var _services_user_user_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../services/user/user.model */ "./src/app/services/user/user.model.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var FormUserComponent = /** @class */ (function () {
    function FormUserComponent(route, router, userService) {
        this.route = route;
        this.router = router;
        this.userService = userService;
        this.user = new _services_user_user_model__WEBPACK_IMPORTED_MODULE_3__["User"]();
    }
    FormUserComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.paramMap.subscribe(function (params) {
            if (params.get('id')) {
                _this.userId = Number(params.get('id'));
                _this.userService.getUserById(_this.userId).subscribe(function (u) {
                    _this.user = u;
                });
            }
            else {
                _this.user = new _services_user_user_model__WEBPACK_IMPORTED_MODULE_3__["User"]();
            }
        });
    };
    FormUserComponent.prototype.save = function () {
        if (this.user.id) {
            this.upDateUser();
        }
        else {
            this.createUser();
        }
    };
    FormUserComponent.prototype.createUser = function () {
        var _this = this;
        this.userService.createUser(this.user).subscribe(function (u) {
            _this.router.navigate(['/user']);
        }, function (err) {
            console.error(err);
        });
    };
    FormUserComponent.prototype.upDateUser = function () {
        var _this = this;
        this.userService.updateUser(this.userId, this.user).subscribe(function (u) {
            _this.router.navigate(['/user']);
        }, function (err) {
            console.error(err);
        });
    };
    FormUserComponent.prototype.voltar = function () {
        window.history.back();
    };
    FormUserComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-form-user',
            template: __webpack_require__(/*! ./form.user.component.html */ "./src/app/components/user/form.user/form.user.component.html"),
            styles: [__webpack_require__(/*! ./form.user.component.scss */ "./src/app/components/user/form.user/form.user.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _services_user_user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"]])
    ], FormUserComponent);
    return FormUserComponent;
}());



/***/ }),

/***/ "./src/app/components/user/search.user/search.user.component.html":
/*!************************************************************************!*\
  !*** ./src/app/components/user/search.user/search.user.component.html ***!
  \************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-title>\n        <button mat-icon-button color=\"accent\" (click)=\"routeFormNewUser()\">\n            <mat-icon>person_add</mat-icon>\n            Adicionar Novo\n        </button>\n    </mat-card-title>\n\n    <mat-card-content>\n      <div *ngIf=\"isError\">\n        <mat-chip-list >\n          <mat-chip style=\"margin-left: 30%\" color=\"warn\" selected=\"true\"><mat-chip-avatar> <mat-icon>error</mat-icon></mat-chip-avatar> {{ error }}</mat-chip>\n        </mat-chip-list>\n      </div>\n    </mat-card-content>\n\n    <mat-card-content>\n      <mat-list-item *ngFor=\"let u of users\">\n        <mat-divider [inset]=\"true\"></mat-divider>\n        <h4 mat-line (click)=\"routeFormUser(u.id)\" ><mat-icon>account_circle</mat-icon> {{u.login}} | {{u.name}} | {{u.profile}}</h4>\n        <mat-divider inset *ngIf=\"!last\"></mat-divider>\n      </mat-list-item>\n    </mat-card-content>\n  </mat-card>\n</div>"

/***/ }),

/***/ "./src/app/components/user/search.user/search.user.component.scss":
/*!************************************************************************!*\
  !*** ./src/app/components/user/search.user/search.user.component.scss ***!
  \************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".demo-list {\n  display: flex;\n  flex-flow: row wrap; }\n  .demo-list .mat-list, .demo-list .mat-nav-list, .demo-list .mat-selection-list {\n    border: 1px solid rgba(0, 0, 0, 0.12);\n    width: 350px;\n    margin: 20px 20px 0 0; }\n  .demo-list h2 {\n    margin-top: 20px; }\n  .demo-list .mat-icon {\n    color: rgba(0, 0, 0, 0.12); }\n  .demo-list .mat-list-icon {\n    color: white;\n    background: rgba(0, 0, 0, 0.3); }\n  .demo-secondary-text {\n  color: rgba(0, 0, 0, 0.54); }\n"

/***/ }),

/***/ "./src/app/components/user/search.user/search.user.component.ts":
/*!**********************************************************************!*\
  !*** ./src/app/components/user/search.user/search.user.component.ts ***!
  \**********************************************************************/
/*! exports provided: SearchUserComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchUserComponent", function() { return SearchUserComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../services/user/user.service */ "./src/app/services/user/user.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SearchUserComponent = /** @class */ (function () {
    function SearchUserComponent(userService, router) {
        this.userService = userService;
        this.router = router;
        // exibicao de mensagem de erro na tela
        this.isError = false;
        // exibicao de loader na tela
        this.exibeProgress = false;
        this.value = 0;
    }
    SearchUserComponent.prototype.routeFormUser = function (id) {
        this.router.navigate(["/user/form/" + id]);
    };
    SearchUserComponent.prototype.routeFormNewUser = function () {
        this.router.navigate(["/user/form"]);
    };
    SearchUserComponent.prototype.back = function () {
        window.history.back();
    };
    SearchUserComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userService.getAllUsers()
            .subscribe(function (res) {
            console.log('>>>> get users res=', res);
            _this.users = res;
            _this.exibeProgress = false;
            // this.router.navigate(['/users'])
        }, function (error) {
            console.log('error service get users ==>', error);
            _this.exibeProgress = false;
            _this.isError = true;
            _this.error = "Have no users";
        });
    };
    SearchUserComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-search-user-component',
            template: __webpack_require__(/*! ./search.user.component.html */ "./src/app/components/user/search.user/search.user.component.html"),
            styles: [__webpack_require__(/*! ./search.user.component.scss */ "./src/app/components/user/search.user/search.user.component.scss")]
        }),
        __metadata("design:paramtypes", [_services_user_user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], SearchUserComponent);
    return SearchUserComponent;
}());



/***/ }),

/***/ "./src/app/services/legal.advice/legal.advice.model.ts":
/*!*************************************************************!*\
  !*** ./src/app/services/legal.advice/legal.advice.model.ts ***!
  \*************************************************************/
/*! exports provided: LegalAdvice */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LegalAdvice", function() { return LegalAdvice; });
var LegalAdvice = /** @class */ (function () {
    function LegalAdvice() {
    }
    return LegalAdvice;
}());



/***/ }),

/***/ "./src/app/services/legal.advice/legal.advice.service.ts":
/*!***************************************************************!*\
  !*** ./src/app/services/legal.advice/legal.advice.service.ts ***!
  \***************************************************************/
/*! exports provided: LegalAdviceService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LegalAdviceService", function() { return LegalAdviceService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _shared_env_config__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../shared/env-config */ "./src/app/shared/env-config.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var URL = _shared_env_config__WEBPACK_IMPORTED_MODULE_2__["default"].api.url + "/api/v1/legal-advice";
var LegalAdviceService = /** @class */ (function () {
    function LegalAdviceService(http) {
        this.http = http;
    }
    LegalAdviceService.prototype.getAllLegalAdvices = function () {
        console.log('getAllLegalAdvices URL=>', URL);
        return this.http.get("" + URL);
    };
    LegalAdviceService.prototype.getLegalAdviceById = function (id) {
        console.log('getLegalAdviceById URL=>', URL);
        return this.http.get(URL + "/" + id);
    };
    LegalAdviceService.prototype.createLegalAdvice = function (legalAdvice) {
        console.log('legalAdvice=>', legalAdvice);
        console.log('createLegalAdvice URL=>', URL);
        return this.http.post("" + URL, legalAdvice);
    };
    LegalAdviceService.prototype.updateLegalAdvice = function (id, legalAdvice) {
        console.log('legalAdvice=>', legalAdvice);
        console.log('updateLegalAdvice URL=>', URL + "/" + id);
        return this.http.post(URL + "/" + id, legalAdvice);
    };
    LegalAdviceService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], LegalAdviceService);
    return LegalAdviceService;
}());



/***/ }),

/***/ "./src/app/services/process/process.model.ts":
/*!***************************************************!*\
  !*** ./src/app/services/process/process.model.ts ***!
  \***************************************************/
/*! exports provided: Process */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Process", function() { return Process; });
var Process = /** @class */ (function () {
    function Process() {
    }
    return Process;
}());



/***/ }),

/***/ "./src/app/services/process/process.service.ts":
/*!*****************************************************!*\
  !*** ./src/app/services/process/process.service.ts ***!
  \*****************************************************/
/*! exports provided: ProcessService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProcessService", function() { return ProcessService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _shared_env_config__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../shared/env-config */ "./src/app/shared/env-config.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var URL = _shared_env_config__WEBPACK_IMPORTED_MODULE_2__["default"].api.url + "/api/v1/processes";
var ProcessService = /** @class */ (function () {
    function ProcessService(http) {
        this.http = http;
    }
    ProcessService.prototype.getAllProcesses = function () {
        console.log('getAllProcesses URL=>', URL);
        return this.http.get("" + URL);
    };
    ProcessService.prototype.getProcessById = function (id) {
        console.log('getProcessById URL=>', URL);
        return this.http.get(URL + "/" + id);
    };
    ProcessService.prototype.createProcess = function (process) {
        console.log('user=>', process);
        console.log('createProcess URL=>', URL);
        return this.http.post("" + URL, process);
    };
    ProcessService.prototype.updateProcess = function (id, process) {
        console.log('user=>', process);
        console.log('updateUser URL=>', URL + "/" + id);
        return this.http.post(URL + "/" + id, process);
    };
    ProcessService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], ProcessService);
    return ProcessService;
}());



/***/ }),

/***/ "./src/app/services/user/user.model.ts":
/*!*********************************************!*\
  !*** ./src/app/services/user/user.model.ts ***!
  \*********************************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User() {
    }
    return User;
}());



/***/ }),

/***/ "./src/app/services/user/user.service.ts":
/*!***********************************************!*\
  !*** ./src/app/services/user/user.service.ts ***!
  \***********************************************/
/*! exports provided: UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return UserService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var _shared_env_config__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../shared/env-config */ "./src/app/shared/env-config.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var URL = _shared_env_config__WEBPACK_IMPORTED_MODULE_3__["default"].api.url + "/api/v1/users";
var UserService = /** @class */ (function () {
    function UserService(http) {
        this.http = http;
    }
    UserService.prototype.getAllUsers = function () {
        console.log('getAllUsers URL=>', URL);
        return this.http.get("" + URL);
    };
    UserService.prototype.getUserById = function (id) {
        console.log('getAllUsers URL=>', URL);
        return this.http.get(URL + "/" + id);
    };
    UserService.prototype.createUser = function (user) {
        console.log('user=>', user);
        console.log('createUser URL=>', URL);
        return this.http.post("" + URL, user);
    };
    UserService.prototype.updateUser = function (id, user) {
        console.log('user=>', user);
        console.log('updateUser URL=>', URL + "/" + id);
        return this.http.post(URL + "/" + id, user);
    };
    UserService.prototype.getUsersMock = function () {
        var usersMocked = [
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } },
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } },
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } }
        ];
        return rxjs__WEBPACK_IMPORTED_MODULE_2__["Observable"].create(function (observer) {
            observer.next(usersMocked);
            observer.complete();
        });
    };
    UserService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], UserService);
    return UserService;
}());



/***/ }),

/***/ "./src/app/shared/env-config.ts":
/*!**************************************!*\
  !*** ./src/app/shared/env-config.ts ***!
  \**************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../environments/environment */ "./src/environments/environment.ts");

/* harmony default export */ __webpack_exports__["default"] = ({
    api: {
        url: _environments_environment__WEBPACK_IMPORTED_MODULE_0__["environment"].apiUrl
    }
});


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
var environment = {
    production: false,
    apiUrl: "http://localhost:3000"
};


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
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_4__);





if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /Users/danilopaixao/repositorio-github/softplan-desafio-fullstack/client/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map