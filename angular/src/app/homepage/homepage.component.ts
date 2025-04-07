import { Component, OnInit } from '@angular/core';
import { BookCatalogService } from '../services/book-catalog.service';
import { first } from 'rxjs/operators';
import { CartService } from '../services/cart.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  books: any[] = [];

  constructor(private bookService: BookCatalogService, private cartService: CartService) { }

  ngOnInit(): void {
    this.init();
  }

  init(): void {
    this.bookService.getBooks()
      .pipe(first())
      .subscribe(
        books => {
          this.books = books || []; // Ensure books is not null
          this.books = this.books.slice(0, 4);
        },
        error => {
          console.error('Error fetching books:', error);
        }
      );
  }

  async addToCart(book: any) {
    book.itemId = book.id;
    book.name = book.bookName;
    book.quantity = 1;
    await firstValueFrom(this.cartService.addToCart(book))   
  }
}
