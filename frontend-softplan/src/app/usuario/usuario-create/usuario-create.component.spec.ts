import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioCreateComponent } from './usuario-create.component';

describe('UsuarioCreateComponent', () => {
  let component: UsuarioCreateComponent;
  let fixture: ComponentFixture<UsuarioCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuarioCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuarioCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
