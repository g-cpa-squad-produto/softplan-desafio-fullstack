import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from './user';
import { UserService } from './user.service';
import { ToastService } from '../shared/toast/toast.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'process-user',
  templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {

  formUser: FormGroup;

  constructor(private formBuilder: FormBuilder, 
              private userService: UserService,
              private toastService: ToastService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.formUser = this.formBuilder.group({
      idUser: '',
      name: ['', [Validators.required, Validators.maxLength(50)]],
      login: ['', [Validators.required, Validators.maxLength(20)]],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });

    if (this.activatedRoute.snapshot.params.id) {
      const id = this.activatedRoute.snapshot.params.id;
      this.loadById(id);
    }
  }

  loadById(id: number) {
    this.userService.findById(id).subscribe(
      res => this.formUser.patchValue(res)
    );
  }

  isUpdate() {
    return !!this.formUser.get('idUser').value;
  }

  save() {
    const user = this.formUser.getRawValue() as User;
    this.userService.insert(user).subscribe(
      () => {
        this.toastService.showSuccess('Usuário salvo com sucesso!');
        this.formUser.reset();
        this.router.navigate(['/home']);
      },
      (error) => this.toastService.showError(error.error[0].mensagemUsuario)
    )
  }

  disabled() {
    if (this.isUpdate()) {
      return !this.formUser.get('name').value || !this.formUser.get('login').value || !this.formUser.get('role').value;
    } else {
      return this.formUser.invalid;
    }
  }
}