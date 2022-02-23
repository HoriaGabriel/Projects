import { Component, OnInit } from '@angular/core';
import {Book} from "../models/book.models";
import {UserService} from "../_services/user.service";

@Component({
  selector: 'app-book-return',
  templateUrl: './book-return.component.html',
  styleUrls: ['./book-return.component.css']
})
export class BookReturnComponent implements OnInit {

  user: string = '';
  book: string = '';
  returned = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  updateReservation(): void {
    const data = {
      book: this.book,
      user: this.user
    };

    this.userService.updateRes(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.returned = true;
        },
        error: (e) => console.error(e)
      });
  }

  newReservation(): void {
    this.returned = false;
    this.book = '';
    this.user='';
  }

}
