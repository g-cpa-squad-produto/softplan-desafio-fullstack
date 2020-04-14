import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { Roles } from 'src/app/models/roles.model';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  user: any;
  userId: string;
  userForm: FormGroup;
  formErrors: any;
  isUpdate = false;
  showLoadingBar: Boolean = false;
  roles: Array<Roles>;
  
  selectable = true;
  removable = true;

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  routeListen: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    public snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: any) => {
        console.log(params);
        if (params['id'] !== 'novo') {
          this.userId = params['id'];
          this.isUpdate = true;
        }
      });

    this.userService.getUser(this.userId).subscribe((userForm) => {this.user = userForm; this.userForm.patchValue(this.user)});

    this.userForm = this.createUserForm();
  
    this.userService.getRoles()
    .subscribe(roles => this.roles = roles);
  }

  createUserForm(): FormGroup {
    return this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      roles: ['', Validators.required],
    });
  }

  save(): void {
    this.showLoadingBar = true;
    if (this.isUpdate) {
      this.updateUser();
    } else {
      this.registerUser();
    }
  }

  updateUser(): void {
    if (this.userForm.valid) {
      const data = new User(this.userForm.getRawValue());
      this.showLoadingBar = true;
      this.userService.updateUser(this.user.userId, data)
        .subscribe(
          response => {
            this.showMessage('Dados atualizados com sucesso!', 'Ok');
            this.showLoadingBar = false;
          },
          error => {
            this.showMessage('Ops! Houve um erro.', 'Tentar novamente');
            this.showLoadingBar = false;
          }
        );
    }
  }

  registerUser(): void {
    if (this.userForm.valid) {
      let user = new User(this.userForm.getRawValue());
      this.showLoadingBar = true;
      this.userService
        .createUser(user)
        .subscribe(
          response => {
            this.showMessage('Registro salvo com sucesso!', 'Ok');
            this.showLoadingBar = false;
          },
          error => {
            this.showMessage('Ops! Houve um erro.', 'Tentar novamente');
            this.showLoadingBar = false;
          }
        );
    }
  }

  showMessage(message: string, type: string = 'Ok'): void {
    this.snackBar.open(message, type, {
      duration: 4200,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      panelClass: type === 'Ok' ? 'snackbar-dialog-success' : 'snackbar-dialog-error'
    });
  }

  goBack(): void {
    this.router.navigate(['/users']);
  }

  removeUser(): void {
    this.userService
      .deleteUser(this.user.user_id)
      .subscribe(
        (res: any) => {
          this.snackBar.open('Registro Exluído com Sucesso!', 'Ok', {
            duration: 4200,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
            panelClass: 'snackbar-dialog-success'
          });

          this.router.navigate(['/users']);
        },
        error => {
          this.snackBar.open(`Ops! Houve um erro. [${error.message}]`, 'Tentar novamente', {
            duration: 4200,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
            panelClass: 'snackbar-dialog-error'
          });
        });
  }
}
