// Core Modules
import { NgModule, ClassProvider } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CdkTableModule } from '@angular/cdk/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// teste zoom image on IE
import { ImageViewerComponent } from './components/imageviewer/imageviewer.component';
import { ImageViewerModule } from '@hallysonh/ngx-imageviewer';

import { CustomToolBarComponent } from './components/custom-toolbar/custom.toolbar.component';

// Material UI
import {
  MatToolbarModule,
  MatCardModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  MatListModule,
  MatDividerModule,
  MatChipsModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatExpansionModule,
  MatAutocompleteModule,
  MatSelectModule,
  MatMenuModule,
  MatStepperModule,
  MatSidenavModule,
  MatBadgeModule,
  MatDialogModule,
  MatTreeModule
} from '@angular/material';

import {FlexLayoutModule} from '@angular/flex-layout';

// Rotas
import { AppRoutingModule } from './app-routing.module';

// Components
import { AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {SearchUserComponent} from './components/user/search.user/search.user.component';
import { FormUserComponent } from './components/user/form.user/form.user.component';

import {SearchProcessComponent} from './components/process/search.process/search.process.component';
import { FormProcessComponent } from './components/process/form.process/form.process.component';

import {SearchLegalAdviceComponent} from './components/legal.advice/search.legal.advice/search.legal.advice.component';
import { FormLegalAdviceComponent } from './components/legal.advice/form.legal.advice/form.legal.advice.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SearchUserComponent,
    FormUserComponent,
    SearchProcessComponent,
    FormProcessComponent,
    SearchLegalAdviceComponent,
    FormLegalAdviceComponent,
    ImageViewerComponent,
    CustomToolBarComponent
  ],
  imports: [
    // ##CORE
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    CdkTableModule,
    FormsModule,
    ReactiveFormsModule,
    // ##MATERIAL
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatDividerModule,
    MatChipsModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    MatExpansionModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatMenuModule,
    MatStepperModule,
    MatSidenavModule,
    MatBadgeModule,
    MatDialogModule,
    FlexLayoutModule,
    // ##MATERIAL

    // ##IMAGE VIEWER
    ImageViewerModule,

    AppRoutingModule // ROTAS
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
