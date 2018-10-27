import { Process } from './../../../model/process';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { ProcessService } from './../../../core/service/process.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-screening',
  templateUrl: './form-screening.component.html',
  styleUrls: ['./form-screening.component.css']
})
export class FormScreeningComponent implements OnInit {

  public id: number;
  public process: Process;

  public status = ['PENDENTE', 'FINALIZADO'];
  public formProcess: FormGroup;

  constructor( private formBuilder: FormBuilder,
    private activeRoute: ActivatedRoute,
    private massagesServer: MassegesService,
    private proccessService: ProcessService,
    private route: Router) { }

  ngOnInit() {
    this.createForm();
    this.id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    if (this.id) {
      this.proccessService.findById(this.id).subscribe(process => {
        this.process = new Process(process);
        this.formProcess.patchValue(process);
      });
    }
  }

  public createForm() {
    if (this.id) {
      this.formProcess = this.formBuilder.group({
        id: [],
        code: ['', Validators.required],
        status: ['', Validators.required],
        name: ['', Validators.required]
      });
  } else {
    this.formProcess = this.formBuilder.group({
      id: [],
      code: ['', Validators.required],
      status: ['', Validators.required],
      name: ['', Validators.required]
    });
  }
  }


  public create() {
    const process = new Process(this.formProcess.value);
    this.proccessService.salve(process).subscribe(() => {
        this.massagesServer.success('Processo salvo com sucesso', 'Sucesso');
    });
  }

  public update() {}

}
