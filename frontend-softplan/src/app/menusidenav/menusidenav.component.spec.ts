import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenusidenavComponent } from './menusidenav.component';

describe('MenusidenavComponent', () => {
  let component: MenusidenavComponent;
  let fixture: ComponentFixture<MenusidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenusidenavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenusidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
