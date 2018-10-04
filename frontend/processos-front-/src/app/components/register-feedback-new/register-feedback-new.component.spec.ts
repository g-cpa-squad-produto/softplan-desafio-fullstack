import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterFeedbackNewComponent } from './register-feedback-new.component';

describe('RegisterFeedbackNewComponent', () => {
  let component: RegisterFeedbackNewComponent;
  let fixture: ComponentFixture<RegisterFeedbackNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterFeedbackNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFeedbackNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
