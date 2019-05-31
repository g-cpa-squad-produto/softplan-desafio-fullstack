import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UsuarioDetailComponent} from './usuario-detail.component';

describe('View', () => {
    let component: UsuarioDetailComponent;
    let fixture: ComponentFixture<UsuarioDetailComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [UsuarioDetailComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UsuarioDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
