import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GridUsuarioComponent } from './grid-usuario.component';

describe('GridUsuarioComponent', () => {
  let component: GridUsuarioComponent;
  let fixture: ComponentFixture<GridUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GridUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
