import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroisListComponent } from './components/herois-list/herois-list.component';
import { HeroiFormComponent } from './components/heroi-form/heroi-form.component';
import { SuperpoderesListComponent } from './components/superpoderes-list/superpoderes-list.component';
import { SuperpoderFormComponent } from './components/superpoder-form/superpoder-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HeroisListComponent,
    HeroiFormComponent,
    SuperpoderesListComponent,
    SuperpoderFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
