import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoVisualizarComponent } from './processo-visualizar.component';

describe('ProcessoVisualizarComponent', () => {
  let component: ProcessoVisualizarComponent;
  let fixture: ComponentFixture<ProcessoVisualizarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessoVisualizarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoVisualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
