import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessesComponent } from './processes.component';

describe('UsersComponent', () => {
  let component: ProcessesComponent;
  let fixture: ComponentFixture<ProcessesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
