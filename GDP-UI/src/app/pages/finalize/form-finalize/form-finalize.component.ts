import { Feedback } from './../../../model/feedback';
import { FinalizeService } from './../../../core/service/finalize.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Process } from 'src/app/model/process';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MassegesService } from 'src/app/core/messeges/messages.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-finalize',
  templateUrl: './form-finalize.component.html',
  styleUrls: ['./form-finalize.component.css']
})
export class FormFinalizeComponent implements OnInit {

  users: User[];
  process: Process;
  formFeedback: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private massagesServer: MassegesService,
    private finalizeService: FinalizeService,
    private route: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.process = this.activatedRoute.snapshot.data['process'];
    this.createForm();
    this.formFeedback.patchValue(this.process);
  }

  public createForm() {
      this.formFeedback = this.formBuilder.group({
        name: [{value: '', disabled: true}],
        status: [{value: '', disabled: true}],
        user: ['', Validators.required],
        description: ['', Validators.required]

      });
  }

  public finalize() {
    const feedback: Feedback = new Feedback(this.formFeedback.value);
    feedback.process = this.process;
    this.finalizeService.salve(feedback).subscribe(res => {
        this.massagesServer.success('Parecar salvo com sucesso', 'Sucesso');
        this.route.navigate(['finalizar']);
    });

  }

}
