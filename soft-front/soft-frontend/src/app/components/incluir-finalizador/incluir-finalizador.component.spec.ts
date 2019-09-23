import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncluirFinalizadorComponent } from './incluir-finalizador.component';

describe('IncluirFinalizadorComponent', () => {
  let component: IncluirFinalizadorComponent;
  let fixture: ComponentFixture<IncluirFinalizadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncluirFinalizadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncluirFinalizadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
