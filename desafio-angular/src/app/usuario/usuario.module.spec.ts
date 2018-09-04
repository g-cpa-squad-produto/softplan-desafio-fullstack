import { UsuarioModule } from './usuario.module';

describe('UsuarioModule', () => {
  let usuarioModule: UsuarioModule;

  beforeEach(() => {
    usuarioModule = new UsuarioModule();
  });

  it('should create an instance', () => {
    expect(usuarioModule).toBeTruthy();
  });
});
