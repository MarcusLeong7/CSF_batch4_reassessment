import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PizzaService} from "../../pizza.service";
import {OrderSummary} from "../../models";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  // Dependency Injections
  private activatedRoute = inject(ActivatedRoute);
  private pizzaSvc = inject(PizzaService);

  email: string = "Not set"
  orders!: Promise<OrderSummary[]>;

  constructor() { }

  ngOnInit(): void {
    this.email = this.activatedRoute.snapshot.params['email'];
    console.log(this.email);
    this.orders = this.pizzaSvc.getOrders(this.email);
  }



}
