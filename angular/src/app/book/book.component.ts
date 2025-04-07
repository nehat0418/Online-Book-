import { Component, OnInit } from '@angular/core';
import { BookCatalogService } from '../services/book-catalog.service';
import { first, firstValueFrom } from 'rxjs';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css'],
})
export class BookComponent implements OnInit {
  books: any[] = [];
  newBook: any = {};

  constructor(private bookService: BookCatalogService, private cartService: CartService) {}

  ngOnInit() {
    this.getBooks();
  }

  getBooks() {
    this.bookService.getBooks()
    .pipe(first())
    .subscribe(
      books => {
        this.books = books || []; // Ensure books is not null
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
