import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.models';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})

export class AddBookComponent implements OnInit {

  book: Book = {
    name: '',
    isbn: '',
    available:'',
    genre:'',
    publisher:'',
    author:''
  };
  submitted = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  saveBook(): void {
    const data = {
      name: this.book.name,
      isbn: this.book.isbn,
      available: this.book.available,
      genre: this.book.genre,
      publisher: this.book.publisher,
      author: this.book.author
    };

    this.userService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newBook(): void {
    this.submitted = false;
    this.book = {
      name: '',
      isbn: '',
      available:'',
      genre:'',
      publisher:'',
      author:''
    };
  }

}

