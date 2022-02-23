import { Component, OnInit } from '@angular/core';
import {Reservation} from "../models/reservation.model";
import {UserService} from "../_services/user.service";
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-not-returned-books',
  templateUrl: './not-returned-books.component.html',
  styleUrls: ['./not-returned-books.component.css']
})
export class NotReturnedBooksComponent implements OnInit {

  reservations?: Reservation[];
  currentReservation: Reservation = {};
  currentIndex = -1;
  currentUser: any = '';

  constructor(private userService: UserService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser().username;
    this.retrieveReservations();
  }

  retrieveReservations(): void {
    this.userService.getAll4(this.currentUser)
      .subscribe({
        next: (data) => {
          this.reservations = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  setActiveReservation(reservation: Reservation, index: number): void {
    this.currentReservation = reservation;
    this.currentIndex = index;
  }

}
