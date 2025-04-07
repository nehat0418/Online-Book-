import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  cartItems: any[] = []; // Array to store cart items
  total: number = 0; // Total cart amount

  constructor(private router: Router, private cartService: CartService){}

  ngOnInit() {
    this.fetchCart();
  }

  calculateTotal() {
    this.total = 0;
    for (let item of this.cartItems) {
      this.total += item.quantity * item.price;
    }
  }

  async fetchCart() {
    this.cartItems = await firstValueFrom(this.cartService.getCartItems())
    this.calculateTotal();
  }

  proceedToPayment() {
    if (this.cartItems.length > 0) {
      
      this.router.navigate(['/payment'], { state: { cartItems: this.cartItems, total: this.total } });
    }
  }

  async updateCart(type: string, book: any) {
    book.quantity = 1;
    try {
    if(type=="increment") {
      await firstValueFrom(this.cartService.addToCart(book))
    } else {
      await firstValueFrom(this.cartService.removeFromCart(book))
    }
  } catch(e) {
    console.error(e)
  }
    this.fetchCart();
  }
  
  }
