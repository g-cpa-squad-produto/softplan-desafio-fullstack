import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessosComponent } from './processos.component';

describe('ProcessosComponent', () => {
  let component: ProcessosComponent;
  let fixture: ComponentFixture<ProcessosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
