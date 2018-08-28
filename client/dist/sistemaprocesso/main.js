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
/* harmony import */ var _components_login_login_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./components/login/login.component */ "./src/app/components/login/login.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var appRoutes = [
    {
        path: '',
        component: _components_login_login_component__WEBPACK_IMPORTED_MODULE_4__["LoginComponent"]
        // component: OnlyForDevComponent
    },
    {
        path: 'user',
        component: _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_2__["SearchUserComponent"]
    },
    {
        path: 'user/form',
        component: _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_3__["FormUserComponent"]
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

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-toolbar color=\"primary\">\n  <mat-toolbar-row>\n    <span class=\"header-spacer\"></span>\n    <span>\n      <mat-chip-list>\n          <mat-chip color=\"accent\" selected (click)=\"start.toggle()\"><mat-icon>menu</mat-icon></mat-chip>\n      </mat-chip-list>\n    </span>\n  </mat-toolbar-row>\n</mat-toolbar>\n<mat-drawer-container class=\"demo-drawer-container\">\n    <mat-drawer #start mode=\"over\">\n    <mat-nav-list>\n      <a mat-list-item [routerLink]=\"['partner']\" (click)=\"start.close()\">Pessoa</a>\n      <a mat-list-item [routerLink]=\"['partner/form']\" (click)=\"start.close()\">Pessoa Cadastro</a>\n    </mat-nav-list>\n    </mat-drawer>\n\n    <div class=\"content-center\" ngClass.lg=\"content-lg\">\n      <router-outlet #o=\"outlet\"></router-outlet>\n    </div>\n</mat-drawer-container>"

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
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")],
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
/* harmony import */ var _components_only_for_dev_only_for_dev_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./components/only-for-dev/only-for-dev.component */ "./src/app/components/only-for-dev/only-for-dev.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./components/user/search.user/search.user.component */ "./src/app/components/user/search.user/search.user.component.ts");
/* harmony import */ var _components_login_login_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./components/login/login.component */ "./src/app/components/login/login.component.ts");
/* harmony import */ var _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./components/user/form.user/form.user.component */ "./src/app/components/user/form.user/form.user.component.ts");
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
                _app_component__WEBPACK_IMPORTED_MODULE_11__["AppComponent"],
                _components_login_login_component__WEBPACK_IMPORTED_MODULE_13__["LoginComponent"],
                _components_user_search_user_search_user_component__WEBPACK_IMPORTED_MODULE_12__["SearchUserComponent"],
                _components_user_form_user_form_user_component__WEBPACK_IMPORTED_MODULE_14__["FormUserComponent"],
                _components_imageviewer_imageviewer_component__WEBPACK_IMPORTED_MODULE_6__["ImageViewerComponent"],
                _components_only_for_dev_only_for_dev_component__WEBPACK_IMPORTED_MODULE_8__["OnlyForDevComponent"]
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
                // ##MATERIAL
                // ##IMAGE VIEWER
                _hallysonh_ngx_imageviewer__WEBPACK_IMPORTED_MODULE_7__["ImageViewerModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_10__["AppRoutingModule"] // ROTAS
            ],
            bootstrap: [
                _app_component__WEBPACK_IMPORTED_MODULE_11__["AppComponent"]
            ]
        })
    ], AppModule);
    return AppModule;
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

module.exports = "  <div>\n  <mat-card>\n    <mat-card-title><mat-card-title><mat-icon>lock</mat-icon> Login  </mat-card-title></mat-card-title>\n\n    <mat-card-content>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"User\" id=\"user\" [(ngModel)]=\"user\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Password\" type=\"password\" id=\"password\" [(ngModel)]=\"password\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\" *ngIf=\"loginError\">\n        <mat-chip-list>\n          <mat-chip selected=\"true\" color=\"warn\">\n            <mat-icon>warning</mat-icon>\n            {{ loginError }}\n          </mat-chip>\n        </mat-chip-list>\n      </div>\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\">\n        <span class=\"send\">\n          <button mat-fab type=\"submit\" id=\"login\" (click)=\"login(user, password)\">\n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    </mat-card-content>\n\n  </mat-card>\n</div>\n\n"

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

/***/ "./src/app/components/only-for-dev/only-for-dev.component.css":
/*!********************************************************************!*\
  !*** ./src/app/components/only-for-dev/only-for-dev.component.css ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".tab-solicitation{\n    width: 100%;\n}\n\n.zoom-beta-0{\n    width: 100px;\n    height: 100px;\n}\n\n.zoom-beta-1{\n    width: 100px;\n    height: 100px;\n}\n\n.zoom-beta-2{\n    width: 200px;\n    height: 200px;\n}\n\n.zoom-beta-3{\n    width: 300px;\n    height: 300px;\n}\n\n.zoom-beta-4{\n    width: 400px;\n    height: 400px;\n}\n\n.zoom-beta-5{\n    width: 500px;\n    height: 500px;\n}\n\n.zoom-beta-6{\n    width: 600px;\n    height: 600px;\n}"

/***/ }),

/***/ "./src/app/components/only-for-dev/only-for-dev.component.html":
/*!*********************************************************************!*\
  !*** ./src/app/components/only-for-dev/only-for-dev.component.html ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"form-register\">\n\n        <!-- <app-imageviewer [urlSrc]=\"imgUrl\"></app-imageviewer> -->\n \n   <div style=\"width: 350px; height: 350px; overflow: scroll;\">\n    <img [src]=\"imgSrc\" id=\"imgTeste\" name=\"imgTeste\" class=\"zoom-beta-0\"  >\n   </div>\n    \n    \n    <div>\n        <button mat-button warn color=\"warn\" (click)=\"zoomLess()\">\n            <mat-icon>zoom_out</mat-icon>\n          </button>\n  \n          <button mat-button primary color=\"primary\" (click)=\"zoomMore()\" >\n            <mat-icon>zoom_in</mat-icon>\n          </button>\n    </div>\n\n\n\n</div>"

/***/ }),

/***/ "./src/app/components/only-for-dev/only-for-dev.component.ts":
/*!*******************************************************************!*\
  !*** ./src/app/components/only-for-dev/only-for-dev.component.ts ***!
  \*******************************************************************/
/*! exports provided: OnlyForDevComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "OnlyForDevComponent", function() { return OnlyForDevComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var OnlyForDevComponent = /** @class */ (function () {
    // './assets/img/interfile-site.png';
    function OnlyForDevComponent() {
        this.zoomIndex = 1;
        this.imgSrc = 'https://1.bp.blogspot.com/-3-AeZEPxb74/VD_YJB2D0ZI/AAAAAAAAABU/JWQpPf9BPw8/s1600/IMG_41862524318112.jpeg';
    }
    OnlyForDevComponent.prototype.ngOnInit = function () {
    };
    OnlyForDevComponent.prototype.zoomMore = function () {
        console.log('CHAMOU ZOOM+');
        var imgTeste = document.getElementsByName('imgTeste')[0];
        this.zoomIndex++;
        imgTeste.classList.add("zoom-beta-" + this.zoomIndex);
    };
    OnlyForDevComponent.prototype.zoomLess = function () {
        console.log('CHAMOU ZOOM-');
        var imgTeste = document.getElementsByName('imgTeste')[0];
        console.log('imgTeste', imgTeste);
        imgTeste.classList.remove("zoom-beta-" + this.zoomIndex);
        this.zoomIndex--;
    };
    OnlyForDevComponent.prototype.zoom = function (e) {
        if (e.deltaY < 0) {
            this.zoomLess();
        }
        else {
            this.zoomMore();
        }
    };
    OnlyForDevComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-only-for-dev',
            template: __webpack_require__(/*! ./only-for-dev.component.html */ "./src/app/components/only-for-dev/only-for-dev.component.html"),
            styles: [__webpack_require__(/*! ./only-for-dev.component.css */ "./src/app/components/only-for-dev/only-for-dev.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], OnlyForDevComponent);
    return OnlyForDevComponent;
}());



/***/ }),

/***/ "./src/app/components/user/form.user/form.user.component.html":
/*!********************************************************************!*\
  !*** ./src/app/components/user/form.user/form.user.component.html ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-card>\n    <mat-card-content >\n        <mat-card-title >\n          User\n        </mat-card-title>\n    </mat-card-content>\n\n    <mat-card-content>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"User\" id=\"user\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput placeholder=\"Login\" id=\"login\" required>\n        </mat-form-field>\n      </div>\n      <div fxLayout=\"row\" fxLayout=\"column\">\n        <mat-form-field>\n          <input matInput type=\"password\" placeholder=\"Password\" id=\"password\" required>\n        </mat-form-field>\n      </div>\n\n      <div fxLayout=\"row\" fxLayoutAlign=\"end\">\n        <span class=\"send\">\n          <button mat-fab> \n            <mat-icon>send</mat-icon>\n          </button>\n        </span>\n      </div>\n    </mat-card-content>\n\n  </mat-card>\n</div>\n"

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
    function FormUserComponent() {
    }
    FormUserComponent.prototype.ngOnInit = function () {
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
        __metadata("design:paramtypes", [])
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

module.exports = "<div>\n  <mat-card>\n    <mat-card-title>\n        <button mat-icon-button color=\"accent\" (click)=\"routeFormUser()\">\n            <mat-icon>person_add</mat-icon>\n            Add User\n        </button>\n    </mat-card-title>\n\n    <mat-card-content>\n      <div *ngIf=\"isError\">\n        <mat-chip-list >\n          <mat-chip style=\"margin-left: 30%\" color=\"warn\" selected=\"true\"><mat-chip-avatar> <mat-icon>error</mat-icon></mat-chip-avatar> {{ error }}</mat-chip>\n        </mat-chip-list>\n      </div>\n      <form class=\"form\">\n        <mat-form-field color=\"accent\" style=\"width: 80%\">\n          <input matInput type=\"text\" placeholder=\"Search Users\" style=\"width: 80%\">\n        </mat-form-field>\n        <mat-spinner diameter=\"55\" ></mat-spinner>\n        <button mat-fab color=\"primary\" style=\"margin: 5px\">\n          <mat-icon>search</mat-icon>\n        </button>\n      </form>\n    </mat-card-content>\n\n    <mat-card-content>\n      <!-- <mat-list-item *ngFor=\"let p of pessoas\">\n        <mat-divider [inset]=\"true\"></mat-divider>\n        <h4 mat-line (click)=\"routeCadastroPessoa()\" >{{p.nome}}</h4>\n        <mat-divider inset *ngIf=\"!last\"></mat-divider>\n      </mat-list-item> -->\n    </mat-card-content>\n  </mat-card>\n</div>"

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
/* harmony import */ var _services_user_user_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../services/user/user.service */ "./src/app/services/user/user.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
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
        this.users = [];
        // exibicao de mensagem de erro na tela
        this.isError = false;
        // exibicao de loader na tela
        this.exibeProgress = false;
        this.value = 0;
    }
    SearchUserComponent.prototype.routeFormUser = function () {
        this.router.navigate(['/user/form']);
    };
    SearchUserComponent.prototype.back = function () {
        window.history.back();
    };
    SearchUserComponent.prototype.ngOnInit = function () {
        var _this = this;
        var isMobile = /Android|iPhone/i.test(window.navigator.userAgent);
        console.log("Plataforma " + window.navigator.userAgent);
        this.users =
            this.userService.getUsers()
                .subscribe(function (res) {
                console.log('>>>> get users res=', res);
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
        __metadata("design:paramtypes", [_services_user_user_service__WEBPACK_IMPORTED_MODULE_1__["UserService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], SearchUserComponent);
    return SearchUserComponent;
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
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var UserService = /** @class */ (function () {
    function UserService() {
    }
    UserService.prototype.getUsers = function () {
        var usersMocked = [
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } },
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } },
            { id: '1', login: 'Luana', perfil: { id: '1', nome: 'perfil A' } }
        ];
        return rxjs__WEBPACK_IMPORTED_MODULE_1__["Observable"].create(function (observer) {
            observer.next(usersMocked);
            observer.complete();
        });
    };
    UserService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [])
    ], UserService);
    return UserService;
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
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
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