import { TestBed } from '@angular/core/testing';

import { ProcessFeedbackService } from './process-feedback.service';

describe('ProcessFeedbackService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProcessFeedbackService = TestBed.get(ProcessFeedbackService);
    expect(service).toBeTruthy();
  });
});
