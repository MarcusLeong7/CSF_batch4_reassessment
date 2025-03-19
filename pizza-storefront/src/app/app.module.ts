import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrderComponent } from './components/order/order.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {PizzaService} from "./pizza.service";

const appRoutes: Routes = [
  // View 0: Home
  { path: '', component: MainComponent},
  // View 1: Category
  { path: 'orders/:email', component: OrderComponent},
  // wild card must be the last route
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [ AppComponent, MainComponent, OrderComponent ],
  imports: [ BrowserModule,HttpClientModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true }) ],
  providers: [PizzaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
