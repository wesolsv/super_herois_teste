import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroisListComponent } from './components/herois-list/herois-list.component';
import { HeroiFormComponent } from './components/heroi-form/heroi-form.component';
import { SuperpoderesListComponent } from './components/superpoderes-list/superpoderes-list.component';
import { SuperpoderFormComponent } from './components/superpoder-form/superpoder-form.component';

const routes: Routes = [
  { path: 'herois', component: HeroisListComponent },
  { path: 'herois/novo', component: HeroiFormComponent },
  { path: 'herois/editar/:id', component: HeroiFormComponent },
  { path: 'superpoderes', component: SuperpoderesListComponent },
  { path: 'superpoderes/novo', component: SuperpoderFormComponent },
  { path: '', redirectTo: '/herois', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
