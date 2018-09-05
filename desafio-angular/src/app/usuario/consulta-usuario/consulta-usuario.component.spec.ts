import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaUsuarioComponent } from './consulta-usuario.component';

describe('ConsultaUsuarioComponent', () => {
  let component: ConsultaUsuarioComponent;
  let fixture: ComponentFixture<ConsultaUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultaUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
