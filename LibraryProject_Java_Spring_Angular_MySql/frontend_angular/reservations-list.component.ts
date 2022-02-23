import {Component, NgModule, OnInit} from '@angular/core';
import { Reservation } from 'src/app/models/reservation.model';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';


@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})


export class ReservationsListComponent implements OnInit {

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
    this.userService.getAll3(this.currentUser)
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
