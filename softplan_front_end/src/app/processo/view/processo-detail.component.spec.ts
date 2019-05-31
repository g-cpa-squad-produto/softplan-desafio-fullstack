import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProcessoDetailComponent} from './processo-detail.component';

describe('View', () => {
    let component: ProcessoDetailComponent;
    let fixture: ComponentFixture<ProcessoDetailComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProcessoDetailComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ProcessoDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
