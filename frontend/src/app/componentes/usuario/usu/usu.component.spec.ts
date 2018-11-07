import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuComponent } from './usu.component';

describe('UsuComponent', () => {
  let component: UsuComponent;
  let fixture: ComponentFixture<UsuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
