export class Book {
    public bookId: string;
    public userId: string;
    public book: number;
    public feedback: string;
  
    constructor(bookId: string, userId: string, book: number, feedback: string) {
      this.bookId = bookId;
      this.userId = userId;
      this.book = book;
      this.feedback = feedback;
    }
  }
  