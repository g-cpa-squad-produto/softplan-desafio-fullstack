import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcComponent } from './proc.component';

describe('ProcComponent', () => {
  let component: ProcComponent;
  let fixture: ComponentFixture<ProcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
