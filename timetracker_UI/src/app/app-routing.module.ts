import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckInComponent } from './check-in/check-in.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { ViewComponent } from './view/view.component';


const routes: Routes = [
  {path: '', component: CheckInComponent},
  {path: 'checkin', component: CheckInComponent},
  {path: 'checkout', component: CheckOutComponent},
  {path: 'view', component: ViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
