import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoCreateComponent } from './processo-create.component';

describe('ProcessoCreateComponent', () => {
  let component: ProcessoCreateComponent;
  let fixture: ComponentFixture<ProcessoCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessoCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
