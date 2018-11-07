import { TestBed } from '@angular/core/testing';

import { ProcessoService } from './processo.service';

describe('ProcessoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProcessoService = TestBed.get(ProcessoService);
    expect(service).toBeTruthy();
  });
});
