import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {CHARGES, DASHBOARD, INCOMES, LOGIN, PLANS, PREVIOUS_MONTHS} from "./const";
import {PreviousMonthsComponent} from "./previous-months/previous-months.component";
import {ChargesComponent} from "./charges/charges.component";
import {IncomesComponent} from "./incomes/incomes.component";
import {PlansComponent} from "./plans/plans.component";

const routes: Routes = [
  {path: LOGIN, component: LoginComponent},
  {path: DASHBOARD, component: DashboardComponent},
  {path: PREVIOUS_MONTHS, component: PreviousMonthsComponent},
  {path: CHARGES, component: ChargesComponent},
  {path: INCOMES, component: IncomesComponent},
  {path: PLANS, component: PlansComponent},
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
