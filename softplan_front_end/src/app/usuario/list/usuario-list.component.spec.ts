import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UsuarioListComponent} from './usuario-list.component';

describe('List', () => {
    let component: UsuarioListComponent;
    let fixture: ComponentFixture<UsuarioListComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [UsuarioListComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UsuarioListComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
