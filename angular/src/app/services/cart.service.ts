import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = "http://localhost:8081/api/cart"; // Replace with your backend API URL

  constructor(private http: HttpClient, private router: Router, private toastr: ToastrService) { }

  // Method to add an item to the cart
  addToCart(item: any): Observable<any> {
    const userId = sessionStorage.getItem("userId");
    // If the user is not logged in, navigate them to the login page
    if(!userId) {
      this.router.navigate(["/login"]);
    }
    if(userId)
    this.toastr.success('Item added to the cart!', 'Success!');
    //send the post request to backend to add the item to the cart 
    return this.http.post(`${this.apiUrl}/${userId}/add`, item);
  }

  // Method to remove an item from the cart
  removeFromCart(book: any): Observable<any> {
    const userId = sessionStorage.getItem("userId");
    if(!userId) {
      this.router.navigate(["/login"]);
    }
    if(userId)
    this.toastr.success('Item removed to the cart!', 'Success!');
    return this.http.delete(`${this.apiUrl}/${userId}/remove`, {
      body: book
    });
  }
 // Method to get all cart items for the logged-in user
  getCartItems(): Observable<any> {
    const userId = sessionStorage.getItem("userId");
    if(!userId) {
      this.router.navigate(["/login"]);
    }
    return this.http.get(`${this.apiUrl}/${userId}/view`);
  }

  // Method to clear the entire cart for a user
  clearCart(userId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/api/carts/clearCart?userId=${userId}`);
  }
}
