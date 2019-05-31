import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UsuarioFormComponent} from './usuario-form.component';

describe('UsuarioForm', () => {
    let component: UsuarioFormComponent;
    let fixture: ComponentFixture<UsuarioFormComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [UsuarioFormComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UsuarioFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
