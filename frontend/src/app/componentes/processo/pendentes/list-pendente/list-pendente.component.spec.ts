import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPendenteComponent } from './list-pendente.component';

describe('ListPendenteComponent', () => {
  let component: ListPendenteComponent;
  let fixture: ComponentFixture<ListPendenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPendenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPendenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
