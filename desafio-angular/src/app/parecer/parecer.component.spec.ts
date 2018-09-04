import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParecerComponent } from './parecer.component';

describe('ParecerComponent', () => {
  let component: ParecerComponent;
  let fixture: ComponentFixture<ParecerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParecerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParecerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
