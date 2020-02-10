import {Component, OnInit} from '@angular/core';
import {User} from '../../../shared/model/user.model';
import {FormBuilder, Validators} from '@angular/forms';
import {UserService} from '../../../services/user.service';
import {Process} from '../../../shared/model/process.model';
import {ProcessService} from '../../../services/process.service';
import {Report} from '../../../shared/model/report.model';

@Component({
  selector: 'app-process-new',
  templateUrl: './process-new.component.html'
})
export class ProcessNewComponent implements OnInit {

  users: User[];
  message: { type: string, text: string };
  classCss: {};

  editForm = this.fb.group({
    title: [null, [Validators.required]],
    description: [],
    finalizador: [null, [Validators.required]]
  });

  constructor(
    private userService: UserService,
    private processService: ProcessService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit() {
    this.userService.findAllFinalizador().subscribe(
      (users: User[]) => this.users = users,
      error => this.showMessage({
        type: 'error',
        text: 'Servico indisponível'
      }));
  }

  register() {
    this.message = {type: '', text: ''};
    this.create(this.createFromForm());
  }

  private create(userForm: Process) {
    this.processService.create(this.createFromForm()).subscribe((process: Process) => {
      console.log(process);
      this.showMessage({
        type: 'success',
        text: `Registrado ${process.title} com sucesso`
      });
    });
  }

  private createFromForm(): Process {
    const process = new Process();
    process.title = this.editForm.get(['title'])!.value;
    process.description = this.editForm.get(['description'])!.value;
    process.reports = [];

    this.editForm.get(['finalizador']).value.forEach( id => {
      const report = new Report();
      const user = new User();
      user.id = id;
      report.autor = user;
      process.reports.push( report );
    });
    return process;
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

  previousState(): void {
    window.history.back();
  }

  trackById(index: number, item: User): any {
    return item.id;
  }
}
