import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CheckInComponent } from './check-in/check-in.component';
import { ViewComponent } from './view/view.component';
import {BackendApiService} from './services/backend-api.service';
import { HttpClient } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    CheckInComponent,
    ViewComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [HttpClient, BackendApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
