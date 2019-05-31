import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UsuarioForm} from './usuario-form';

describe('UsuarioForm', () => {
    let component: UsuarioForm;
    let fixture: ComponentFixture<UsuarioForm>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [UsuarioForm]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UsuarioForm);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
