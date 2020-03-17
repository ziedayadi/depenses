import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {DASHBOARD, LOGIN} from "./const";

const routes: Routes = [
  {path: LOGIN, component: LoginComponent},
  {path: DASHBOARD, component: DashboardComponent},
  {
    path: '',
    redirectTo: '/'+LOGIN,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
