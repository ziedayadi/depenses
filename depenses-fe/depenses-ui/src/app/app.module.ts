import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from "./login/login.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {DashboardComponent} from './dashboard/dashboard.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { NavbarComponent } from './navbar/navbar.component';
import { PreviousMonthsComponent } from './previous-months/previous-months.component';
import {ChargesComponent, CreateNewChargeDialog} from './charges/charges.component';
import { IncomesComponent } from './incomes/incomes.component';
import { PlansComponent } from './plans/plans.component';
import {RouterModule} from '@angular/router';
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatSelectModule} from "@angular/material/select";
import {MatDialogModule} from "@angular/material/dialog";



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ToolbarComponent,
    NavbarComponent,
    PreviousMonthsComponent,
    ChargesComponent,
    IncomesComponent,
    PlansComponent,
    CreateNewChargeDialog
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    MatToolbarModule,
    MatInputModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSidenavModule,
    MatCardModule,
    MatTableModule,
    MatSortModule,
    MatSelectModule,
    MatDialogModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
