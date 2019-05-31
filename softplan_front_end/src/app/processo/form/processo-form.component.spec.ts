import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProcessoFormComponent} from './processo-form.component';

describe('UsuarioForm', () => {
    let component: ProcessoFormComponent;
    let fixture: ComponentFixture<ProcessoFormComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProcessoFormComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ProcessoFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
