import { TestBed } from '@angular/core/testing';

import { ParecerService } from './parecer.service';

describe('ParecerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParecerService = TestBed.get(ParecerService);
    expect(service).toBeTruthy();
  });
});
