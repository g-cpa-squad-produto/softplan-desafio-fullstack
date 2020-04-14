import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

import { Process } from 'src/app/models/process.model';
import { ProcessService } from 'src/app/services/process.service';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-process-form',
  templateUrl: './process-form.component.html',
  styleUrls: ['./process-form.component.scss']
})
export class ProcessFormComponent implements OnInit {
  process: any;
  processId: number;
  processForm: FormGroup;
  formErrors: any;
  users: any[];
  chipsSpecialities: Array<any> = [];
  isUpdate = false;
  showLoadingBar: Boolean = false;

  selectable = true;
  removable = true;

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  routeListen: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private processService: ProcessService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    public snackBar: MatSnackBar
  ) {
    this.process = new Process({});
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: any) => {
        if (params['id'] !== 'novo') {
          this.processId = params['id'];
          this.isUpdate = true;
        }
      });

    this.processService.getProcess(this.processId).subscribe((process) => { this.process = process; this.processForm.patchValue(this.process); this.chipsSpecialities = this.process.users || []; console.log(this.chipsSpecialities)});

    this.userService.listUsers().subscribe((userslist) => { this.users = userslist.filter((user) => user.roles === 'FINALIZADOR')});

    this.processForm = this.createProcessForm();
  }

  createProcessForm(): FormGroup {
    return this.formBuilder.group({
      number: ['', Validators.required],
      report: [''],
      users: ['']
    });
  }

  addChips() {
    const indexFound = this.users
      .reduce((prev, crr, index) => {
        console.log(this.processForm.get('users').value.userId)
        return crr.userId === this.processForm.get('users').value.userId
          ? index
          : prev;
      }, -1);

    this.users.splice(indexFound, 1);
    this.chipsSpecialities.push(this.processForm.get('users').value);
  }

  remove(chip: any): void {
    if (this.users) {
      this.users.push(chip);
    }

    this.chipsSpecialities = this.chipsSpecialities
      .filter(crrChip => {
        return chip.userId !== crrChip.userId;
      });
  }

  save(): void {
    this.processForm.get('users').patchValue(this.chipsSpecialities);
    this.showLoadingBar = true;
    if (this.isUpdate) {
      this.updateProcess();
    } else {
      this.registerProcess();
    }
  }

  updateProcess(): void {
    if (this.processForm.valid) {
      const data = new Process(this.processForm.getRawValue());
      this.showLoadingBar = true;
      this.processService.updateProcess(this.process.processId, data)
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

  registerProcess(): void {
    if (this.processForm.valid) {
      let process = new Process(this.processForm.getRawValue());
      this.showLoadingBar = true;
      this.processService
        .createProcess(process)
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
    this.router.navigate(['/processes']);
  }

  removeProcess(): void {
    this.processService
      .deleteProcess(this.process.id)
      .subscribe(
        (res: any) => {
          this.snackBar.open('Registro Exluído com Sucesso!', 'Ok', {
            duration: 4200,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
            panelClass: 'snackbar-dialog-success'
          });

          this.router.navigate(['/processes']);
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
