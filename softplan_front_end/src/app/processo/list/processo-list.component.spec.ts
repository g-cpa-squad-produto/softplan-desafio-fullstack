import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProcessoListComponent} from './processo-list.component';

describe('List', () => {
    let component: ProcessoListComponent;
    let fixture: ComponentFixture<ProcessoListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProcessoListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ProcessoListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
