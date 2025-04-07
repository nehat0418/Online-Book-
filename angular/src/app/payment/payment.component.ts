import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  // Initialize the payment object
  payment: Payment = new Payment();
  paymentStatus: string = '';
  paid = false;
  errors: Record<string, string> = {};

  constructor(private router: Router, private paymentService: PaymentService) {}

  processPayment() {
    //Clear any previous errors
    this.errors = {};

    if(!this.payment.CVV.trim()) {
      this.errors["cvv"] = "CVV is required";
    }
    if(this.payment.CVV.trim().length !== 3) {
      this.errors["cvv"] = "CVV is invalid"
    }
    if(!this.payment.cardNumber.trim()){
      this.errors['cardNumber']= "Card number cannot be empty." ;
    }
    if(this.payment.cardNumber.trim().length !== 16) {
      this.errors["cardNumber"] = "Card number is invalid";
    }
    
     // If there are errors, return and do not proceed with payment
    if(Object.keys(this.errors).length){
      return;
    }
    // If there are no errors, initiate the payment transaction
    this.paymentService.initiatePaymentTransaction(this.payment).subscribe(
      () => {
        this.paymentStatus = 'Payment transaction initiated successfully.';
        this.paid = true;
      },
      (error: any) => {
        this.paymentStatus = 'Failed to initiate payment transaction.';
      }
    );
  }

  validatePayment() {
    this.paymentService.validatePaymentInformation(this.payment).subscribe(
      () => {
        this.paymentStatus = 'Payment information is valid.';
        this.router.navigate(['/payment/confirm']);
      },
      (error: any) => {
        this.paymentStatus = 'Invalid payment information.';
      }
    );
  }

  confirmPayment() {
    this.paymentService.confirmPaymentInfo(this.payment).subscribe(
      () => {
        this.paymentStatus = 'Payment confirmed successfully.';
       
      },
      (error: any) => {
        this.paymentStatus = 'Failed to confirm payment.';
      }
    );
  }
}

export class Payment {
  paymentId: string = '';
  paymentMethod: string = '';
  amount: number = 0;
  cardNumber: string = '';
  CVV: string = '';
  expirationDate: string = '';
  status!: string;
}
