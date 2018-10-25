import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioListagemComponent } from './usuario-listagem.component';

describe('UsuarioListagemComponent', () => {
  let component: UsuarioListagemComponent;
  let fixture: ComponentFixture<UsuarioListagemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioListagemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioListagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
