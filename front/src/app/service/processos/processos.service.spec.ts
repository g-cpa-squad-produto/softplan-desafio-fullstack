import { TestBed } from '@angular/core/testing';

import { ProcessosService } from './processos.service';

describe('ProcessosService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProcessosService = TestBed.get(ProcessosService);
    expect(service).toBeTruthy();
  });
});
