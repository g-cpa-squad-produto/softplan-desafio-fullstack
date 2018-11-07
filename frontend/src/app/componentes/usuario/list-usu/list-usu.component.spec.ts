import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUsuComponent } from './list-usu.component';

describe('ListUsuComponent', () => {
  let component: ListUsuComponent;
  let fixture: ComponentFixture<ListUsuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListUsuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListUsuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
