import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  //placeholder for method that will process payment
  processPayment(cartItems: any[], total: number) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = "http://localhost:8082/payment"; 

  constructor(private http: HttpClient, private router: Router) { }

  // Initiates a payment transaction for a user
  initiatePaymentTransaction(payment: Payment): Observable<any> {
    const userId = sessionStorage.getItem("userId");
    return this.http.post(`${this.apiUrl}/transaction/${userId}`, payment);
  }

  validatePaymentInformation(payment: Payment): Observable<any> {
    return this.http.post(`${this.apiUrl}/validate`, payment);
  }

  confirmPaymentInfo(payment: Payment): Observable<any> {
    return this.http.post(`${this.apiUrl}/confirm`, payment);
  }

  getPaymentById(paymentId: string): Observable<Payment> {
    return this.http.get<Payment>(`${this.apiUrl}/${paymentId}`);
  }

  getPaymentForUser() {
    const userId = sessionStorage.getItem("userId");
    if(!userId) {
      this.router.navigate(["/login"])
      return ;
    }
    return this.http.get<Array<Payment>>(`${this.apiUrl}/${userId}/purchases`);
  }
}

export interface Payment {
  paymentId: string;
  paymentMethod: string;
  amount: number;
  cardNumber: string;
  CVV: string;
  expirationDate: string;
  status: string;
}
