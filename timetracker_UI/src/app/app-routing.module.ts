import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckInComponent } from './check-in/check-in.component';
import { ViewComponent } from './view/view.component';


const routes: Routes = [
  {path: '', component: CheckInComponent},
  {path: 'checkin', component: CheckInComponent},
  {path: 'view', component: ViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
