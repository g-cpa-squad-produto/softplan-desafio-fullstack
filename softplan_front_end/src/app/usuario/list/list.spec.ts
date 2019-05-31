import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {List} from './list';

describe('List', () => {
    let component: List;
    let fixture: ComponentFixture<List>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [List]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(List);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
