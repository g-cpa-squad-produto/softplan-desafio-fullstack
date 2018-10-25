import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParecerCreateComponent } from './parecer-create.component';

describe('ParecerCreateComponent', () => {
  let component: ParecerCreateComponent;
  let fixture: ComponentFixture<ParecerCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParecerCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParecerCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
