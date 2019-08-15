import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarParecerComponent } from './cadastrar-parecer.component';

describe('CadastrarParecerComponent', () => {
  let component: CadastrarParecerComponent;
  let fixture: ComponentFixture<CadastrarParecerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarParecerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarParecerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
