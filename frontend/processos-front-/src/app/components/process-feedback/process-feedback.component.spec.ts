import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessFeedbackComponent } from './process-feedback.component';

describe('ProcessFeedbackComponent', () => {
  let component: ProcessFeedbackComponent;
  let fixture: ComponentFixture<ProcessFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
