import { Component } from '@angular/core';
import { PaymentService } from '../services/payment.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent {

  purchases: any[] = [];

  constructor(private paymentService: PaymentService) {}

  ngOnInit() {
    this.init()
  }

  async init() {
    this.purchases = await firstValueFrom(this.paymentService.getPaymentForUser() as any);
  }

}
