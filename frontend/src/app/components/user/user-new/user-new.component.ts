import {Component, OnInit} from '@angular/core';
import {FormBuilder, NgForm, Validators} from '@angular/forms';
import {User} from '../../../shared/model/user.model';
import {SharedService} from '../../../services/shared.service';
import {UserService} from '../../../services/user.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-new',
  templateUrl: './user-new.component.html'
})
export class UserNewComponent implements OnInit {

  editForm = this.fb.group({
    id: [],
    login: [null, [Validators.required]],
    password: [null, [Validators.required]],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    role: []
  });

  user: User = new User();
  message: { type: string, text: string };
  classCss: {};

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private shared: SharedService,
    private fb: FormBuilder) {
  }

  ngOnInit() {
    const id: number = this.route.snapshot.params.id;
    if (id !== undefined) {
      this.findById(id);
    }
  }

  findById(id: number) {
    this.userService.find(id).subscribe((user: User) => {
      this.editForm.patchValue(user);
    }, error => {
      this.showMessage({
        type: 'error',
        text: error.error.errors[0]
      });
    });
  }

  register() {
    this.message = {type: '', text: ''};
    const userForm = this.createFromForm();
    if (userForm.id == null) {
      this.create(userForm);
    } else {
      this.update(userForm);
    }
  }

  private update(userForm: User) {
    this.userService.update(this.createFromForm()).subscribe((user: User) => {
      this.user = new User();
      this.showMessage({
        type: 'success',
        text: `Alterado ${user.firstName} ${user.lastName} com sucesso`
      });
    });
  }

  private create(userForm: User) {
    this.userService.create(this.createFromForm()).subscribe((user: User) => {
      this.user = new User();
      this.showMessage({
        type: 'success',
        text: `Registrado ${user.firstName} ${user.lastName} com sucesso`
      });
    });
  }

  private createFromForm(): User {
    return {
      ...new User(),
      id: this.editForm.get(['id'])!.value,
      login: this.editForm.get(['login'])!.value,
      password: this.editForm.get(['password'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      role: this.editForm.get(['role'])!.value
    };
  }

  private showMessage(message: { type: string, text: string }) {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string) {
    this.classCss = {
      alert: true
    };
    this.classCss[`alert-${type}`] = true;
  }

  getFormGroupClass(isInvalid: boolean, isDirty): {} {
    return {
      formGroup: true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    };
  }

  previousState(): void {
    window.history.back();
  }
}
