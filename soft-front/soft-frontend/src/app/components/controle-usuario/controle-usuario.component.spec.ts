import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControleUsuarioComponent } from './controle-usuario.component';

describe('ControleUsuarioComponent', () => {
  let component: ControleUsuarioComponent;
  let fixture: ComponentFixture<ControleUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControleUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControleUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
