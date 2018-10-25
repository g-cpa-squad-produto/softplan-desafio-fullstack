import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoComponent } from './processo.component';

describe('ProcessoComponent', () => {
  let component: ProcessoComponent;
  let fixture: ComponentFixture<ProcessoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
