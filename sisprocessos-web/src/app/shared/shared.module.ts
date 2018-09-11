import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';

import {FormsModule} from '@angular/forms';
import {NgSelectModule} from '@ng-select/ng-select';
import {MyDatePickerModule} from 'mydatepicker';
import {NgxMaskModule} from 'ngx-mask';
import {BooleanPipe} from './pipe/boolean.pipe';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {PermissaoDirective} from './directive/permissao.directive';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    NgSelectModule, // Componente para criação de combos (select)
    MyDatePickerModule, // Componente para criação de campos de data (datepicker)
    NgxMaskModule.forRoot(), // Componente de máscara
    NgxDatatableModule // Componente para criação de tabelas (datatable)
  ],
  declarations: [
    BooleanPipe,
    PermissaoDirective
  ],
  exports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    NgSelectModule,
    MyDatePickerModule,
    NgxMaskModule,
    NgxDatatableModule,
    BooleanPipe,
    PermissaoDirective
  ]
})
export class SharedModule {
}
