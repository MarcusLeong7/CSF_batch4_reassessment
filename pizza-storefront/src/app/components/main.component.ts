import {Component, inject, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Order} from "../models";
import {PizzaService} from "../pizza.service";
import {Router} from "@angular/router";

const SIZES: string[] = [
    "Personal - 6 inches",
    "Regular - 9 inches",
    "Large - 12 inches",
    "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    // TODO: Task 2
    private fb = inject(FormBuilder);
    private router = inject (Router);
    private pizzaSvc = inject(PizzaService)

    protected form !: FormGroup
    protected toppings!: FormArray

    pizzaSize = SIZES[0]

    constructor() {
    }

    ngOnInit() {
        this.form = this.createForm()
    }

    private createForm() {
        // initialise empty array for line items
        this.toppings = this.fb.array([], Validators.required);
        return this.fb.group({
            name: this.fb.control<string>("", [Validators.required]),
            email: this.fb.control<string>("", [Validators.required]),
            size: this.fb.control<number>(9, [Validators.required]),
            base: this.fb.control<boolean>(false, [Validators.required]),
            sauce: this.fb.control<string>("", [Validators.required]),
            toppings: this.toppings, // string []
            comments: this.fb.control<string>(""), // optional field
        })
    }

    // Process Form
    protected processForm() {
        /* Important: Naming must match models in backend*/
        const order: Order = {
            name: this.form.value.name,
            email: this.form.value.email,
            size: this.form.value.size,
            thickCrust: this.form.value.base,
            sauce: this.form.value.sauce,
            toppings: this.form.value.toppings,
            comments: this.form.value.comments
        }
        console.info('>>> Processing Form', order);
        // Send order to back end
        // Add into mongo
        this.pizzaSvc.createOrder(order).subscribe({
            next: (response: any) => {
                console.log("RESP",response);
                // Order was successful, show the order ID
                const orderId = response.orderId;
                alert(`Order placed successfully! Order ID: ${orderId}`);

                // Only navigate and reset form after successful response
                const email: string = this.form.value.email;
                this.router.navigate(['/orders', email]);
                this.form.reset();
            },
            error: (error) => {
                console.log("ERROR:", error);
                // Order failed, show the error message
                alert(`Error placing order: ${error.message || 'Unknown error'}`);
            }
        });
    }


    onToppingChange(event:any, topping:string) {
        if (event.target.checked) {
            this.toppings.push(this.fb.control(topping));
        } else {
            const index = this.toppings.controls.findIndex(control => control.value === topping);
            if (index >= 0) {
                this.toppings.removeAt(index);
            }
        }
    }


    updateSize(size: string
    ) {
        this.pizzaSize = SIZES[parseInt(size)]
    }

}
