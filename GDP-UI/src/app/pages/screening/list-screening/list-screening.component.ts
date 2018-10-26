import { ActivatedRoute } from '@angular/router';
import { Process } from './../../../model/process';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-screening',
  templateUrl: './list-screening.component.html',
  styleUrls: ['./list-screening.component.css']
})
export class ListScreeningComponent implements OnInit {

  processList: Process[];

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.processList = this.activatedRoute.snapshot.data['processList'];
    console.log(this.processList);
  }

  public atribuir(id: number, index: number) {

  }

}
