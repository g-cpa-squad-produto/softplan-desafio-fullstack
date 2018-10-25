import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParecerListagemComponent } from './parecer-listagem.component';

describe('ParecerListagemComponent', () => {
  let component: ParecerListagemComponent;
  let fixture: ComponentFixture<ParecerListagemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParecerListagemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParecerListagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
