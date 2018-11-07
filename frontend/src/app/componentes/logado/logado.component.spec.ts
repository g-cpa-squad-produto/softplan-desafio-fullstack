import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogadoComponent } from './logado.component';

describe('LogadoComponent', () => {
  let component: LogadoComponent;
  let fixture: ComponentFixture<LogadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
