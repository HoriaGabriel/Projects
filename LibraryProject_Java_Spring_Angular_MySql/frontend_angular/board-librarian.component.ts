import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.models';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-librarian',
  templateUrl: './board-librarian.component.html',
  styleUrls: ['./board-librarian.component.css']
})
export class BoardLibrarianComponent implements OnInit {
  books?: Book[];
  currentBook: Book = {};
  currentIndex = -1;
  title = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {

    this.retrieveBooks();
  }

  retrieveBooks(): void {
    this.userService.getAll2()
      .subscribe({
        next: (data) => {
          this.books = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  setActiveBook(book: Book, index: number): void {
    this.currentBook = book;
    this.currentIndex = index;
  }

  searchBook(): void {
    this.currentBook = {};
    this.currentIndex = -1;

    this.userService.findByTitle2(this.title)
      .subscribe({
        next: (data) => {
          this.books = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
