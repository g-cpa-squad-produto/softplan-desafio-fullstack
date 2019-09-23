import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarParecerComponent } from './adicionar-parecer.component';

describe('AdicionarParecerComponent', () => {
  let component: AdicionarParecerComponent;
  let fixture: ComponentFixture<AdicionarParecerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdicionarParecerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdicionarParecerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
