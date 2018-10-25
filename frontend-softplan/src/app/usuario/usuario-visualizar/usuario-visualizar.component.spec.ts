import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioVisualizarComponent } from './usuario-visualizar.component';

describe('UsuarioVisualizarComponent', () => {
  let component: UsuarioVisualizarComponent;
  let fixture: ComponentFixture<UsuarioVisualizarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioVisualizarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioVisualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
