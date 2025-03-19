import {Order, OrderSummary} from "./models";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PizzaService {

  private http = inject(HttpClient)

  // TODO Task 3 - Do not change the parameter of this method
  // The method may return any type
  createOrder(order: Order){
    // Setup headers for the HTTP request
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.post('/api/order', order, { headers });
  }

  // TODO Task 3 - You are free to add additional parameters to this method
  // Do not change the return type
  getOrders(email:string): Promise<OrderSummary[]> {
    return lastValueFrom(this.http.get<OrderSummary[]>(`/api/orders/${email}/all`));
  }

  /*  export interface OrderSummary {
    orderId: number
    name: string
    email: string
    amount: number
  }*/

}
