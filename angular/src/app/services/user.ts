export class Books {
    public bookId: string;
    public userId: string;
    public rating: number;
    public feedback: string;
  
    constructor(bookId: string, userId: string, rating: number, feedback: string) {
      this.bookId = bookId;
      this.userId = userId;
      this.rating = rating;
      this.feedback = feedback;
    }
  }
  
  export class User {
    public userId: string;
    public name: string;
    public email: string;
    public age: number;
    public books: Books[];
  
    constructor(userId: string, name: string, email: string, age: number, books: Books[]) {
      this.userId = userId;
      this.name = name;
      this.email = email;
      this.age = age;
      this.books = books;
    }
  }
  