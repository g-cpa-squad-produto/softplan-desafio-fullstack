import { Component, OnInit } from '@angular/core';
import { Process } from 'src/app/model/process';
import { ActivatedRoute } from '@angular/router';
import { StatusProcess } from 'src/app/model/status-process';

@Component({
  selector: 'app-list-finalize',
  templateUrl: './list-finalize.component.html',
  styleUrls: ['./list-finalize.component.css']
})
export class ListFinalizeComponent implements OnInit {

  processList: Process[];

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.processList = this.activatedRoute.snapshot.data['processList'];
    this.processList = this.processList.filter((item) => {
          return item.status === StatusProcess.PENDENTE;
    });
  }

}
