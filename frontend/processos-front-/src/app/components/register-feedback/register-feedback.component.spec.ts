import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterFeedbackComponent } from './register-feedback.component';

describe('RegisterFeedbackComponent', () => {
  let component: RegisterFeedbackComponent;
  let fixture: ComponentFixture<RegisterFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
