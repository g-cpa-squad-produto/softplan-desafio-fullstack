import {Component, OnInit} from '@angular/core';
import {User} from '../../../shared/model/user.model';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {SharedService} from '../../../services/shared.service';
import {DialogService} from '../../../dialog.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {

  users: User[];
  message: {};
  classCss: {};

  constructor(
    private dialogService: DialogService,
    private userService: UserService,
    private router: Router,
    private sharedService: SharedService
  ) {
  }

  ngOnInit() {
    this.findAll();
  }

  edit(id: number) {
    this.router.navigate(['/user-new', id]);
  }

  delete(id: number) {
    this.dialogService.confirm('Confirma a exclusão do usuário?')
      .then((canDelete: boolean) => {
        if (canDelete) {
          this.message = {};
          this.userService.delete(id).subscribe(() => {
            this.showMessage({
              type: 'success',
              text: 'Usuário deletado'
            });
            this.findAll();
          }, () => this.showMessage({
            type: 'error',
            text: 'Erro ao excluir usuário'
          }));
        }
      });
  }

  findAll() {
    this.userService.findAll().subscribe(
      (users: User[]) => this.users = users,
      error => this.showMessage({
        type: 'error',
        text: 'Servico indisponível'
      }));
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

}
