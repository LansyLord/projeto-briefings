import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { PrincipalComponent } from './features/principal/principal.component';
import { FormsModule } from '@angular/forms';
import { ClientFormComponent } from './features/principal/client-form/client-form.component';
import { NotificationComponent } from './shared/notification/notification.component';

@NgModule({
  declarations: [
    AppComponent,
    PrincipalComponent,
    ClientFormComponent,
    NotificationComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
