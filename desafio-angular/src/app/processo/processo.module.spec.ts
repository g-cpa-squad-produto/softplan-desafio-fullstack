import { ProcessoModule } from './processo.module';

describe('ProcessoModule', () => {
  let processoModule: ProcessoModule;

  beforeEach(() => {
    processoModule = new ProcessoModule();
  });

  it('should create an instance', () => {
    expect(processoModule).toBeTruthy();
  });
});
