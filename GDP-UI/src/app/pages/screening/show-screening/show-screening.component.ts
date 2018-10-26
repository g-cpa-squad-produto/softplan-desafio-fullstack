import { Component, OnInit } from '@angular/core';
import { Process } from 'src/app/model/process';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-screening',
  templateUrl: './show-screening.component.html',
  styleUrls: ['./show-screening.component.css']
})
export class ShowScreeningComponent implements OnInit {

  process: Process;

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.process = this.activatedRoute.snapshot.data['process'];
  }

}
