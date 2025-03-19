// DO NOT MODIFY OR CHANGE THE FOLLOWING 2 INTERFACES

export interface Order {
  name: string
  email: string
  size: number
  thickCrust: boolean
  sauce: string
  toppings: string[]
  comments: string
}

export interface OrderSummary {
  orderId: number
  name: string
  email: string
  amount: number
}

// You may add your models below 

