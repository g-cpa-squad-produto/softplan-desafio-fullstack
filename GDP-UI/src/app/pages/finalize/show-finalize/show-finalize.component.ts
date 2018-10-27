import { FinalizeService } from './../../../core/service/finalize.service';
import { Feedback } from './../../../model/feedback';
import { Process } from './../../../model/process';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-finalize',
  templateUrl: './show-finalize.component.html',
  styleUrls: ['./show-finalize.component.css']
})
export class ShowFinalizeComponent implements OnInit {

  process: Process;
  feedbaks: Feedback[];

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.process = this.activatedRoute.snapshot.data['process'];
    this.feedbaks = this.activatedRoute.snapshot.data['feedbaks'];
  }


}
