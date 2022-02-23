import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../models/book.models";
import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css']
})
export class ReservationDetailsComponent implements OnInit {


  constructor(private token: TokenStorageService,
              private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) { }

  @Input() viewMode = false;

 currentUser: string = '';

  @Input()currentBook: Book = {
    name: '',
    isbn: '',
    available: '',
    genre: '',
    publisher:'',
    author:''
  };

  message = '';

  ngOnInit(): void {
    this.currentUser = this.token.getUser().username;
    if (!this.viewMode) {
      this.getBook(this.route.snapshot.params["id"]);
    }
  }

  getBook(id: string): void {
    this.userService.get2(id)
      .subscribe({
        next: (data) => {
          this.currentBook = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  saveReservation(): void {

    this.userService.createRes(this.currentBook.id,this.currentUser,this.currentBook)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This book was reserved successfully!';
        },
        error: (e) => {
          console.error(e)
        }
      });
  }
}
