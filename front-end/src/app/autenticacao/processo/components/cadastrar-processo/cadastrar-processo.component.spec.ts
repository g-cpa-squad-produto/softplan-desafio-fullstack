import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarProcessoComponent } from './cadastrar-processo.component';

describe('CadastrarProcessoComponent', () => {
  let component: CadastrarProcessoComponent;
  let fixture: ComponentFixture<CadastrarProcessoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarProcessoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarProcessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
