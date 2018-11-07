import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendenteComponent } from './pendente.component';

describe('PendenteComponent', () => {
  let component: PendenteComponent;
  let fixture: ComponentFixture<PendenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
