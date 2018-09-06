import { ComumModule } from './comum.module';

describe('ComumModule', () => {
  let comumModule: ComumModule;

  beforeEach(() => {
    comumModule = new ComumModule();
  });

  it('should create an instance', () => {
    expect(comumModule).toBeTruthy();
  });
});
