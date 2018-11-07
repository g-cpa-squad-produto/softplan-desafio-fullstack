import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProcComponent } from './list-proc.component';

describe('ListProcComponent', () => {
  let component: ListProcComponent;
  let fixture: ComponentFixture<ListProcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListProcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
