import { Component, OnDestroy, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil, first } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SharedService } from 'src/app/services/shared.service';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
    loginForm: FormGroup;
    loginFormErrors: any;
    returnUrl: string;
    error: string;


    private _unsubscribeAll: Subject<any>;

    constructor(
        private _formBuilder: FormBuilder,
        private router: Router,
        private route: ActivatedRoute,
        private authenticationService: AuthenticationService,
        private sharedService: SharedService
    ) {

        this.loginFormErrors = {
            email: {},
            password: {}
        };

        this._unsubscribeAll = new Subject();
    }

    ngOnInit(): void {
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || ['/dashboard'];

        this.authenticationService.logout();

        this.loginForm = this._formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });

        this.loginForm.valueChanges.pipe(takeUntil(this._unsubscribeAll)).subscribe(() => {
            this.onLoginFormValuesChanged();
        });
    }

    ngOnDestroy(): void {
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();
    }

    onLoginFormValuesChanged(): void {
        for (const field in this.loginFormErrors) {
            if (!this.loginFormErrors.hasOwnProperty(field)) {
                continue;
            }

            this.loginFormErrors[field] = {};

            const control = this.loginForm.get(field);

            if (control && control.dirty && !control.valid) {
                this.loginFormErrors[field] = control.errors;
            }
        }
    }

    onAuthenticate(): any {
        if (this.loginForm.invalid) {
            this.error = 'Preencha com seus dados.';
        }

        if (this.loginForm.valid) {
            this.authenticationService
                .login(this.loginForm.value.email, this.loginForm.value.password)
                .pipe(first())
                .subscribe(
                    () => {
                        this.sharedService.sendLoginEvent();
                        this.router.navigate(['/dashboard']);
                    },
                    (error: any) => {
                        this.loginForm.reset();
                        this.error = error;
                    }
                );
        }
    }
}
