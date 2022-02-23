import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardCustomerComponent } from './board-customer/board-customer.component';
import { BoardLibrarianComponent } from './board-librarian/board-librarian.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { AddBookComponent } from './add-book/add-book.component';
import { ReservationDetailsComponent } from './reservation-details/reservation-details.component';
import { ReservationsListComponent } from './reservations-list/reservations-list.component';
import { BookReturnComponent } from './book-return/book-return.component';
import { NotReturnedBooksComponent } from './not-returned-books/not-returned-books.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'customer', component: BoardCustomerComponent },
  { path: 'customer/reserve/:id', component: ReservationDetailsComponent },
  { path: 'librarian', component: BoardLibrarianComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'librarian/books/:id', component: BookDetailsComponent },
  { path: 'add-book', component: AddBookComponent },
  { path: 'reservations', component: ReservationsListComponent },
  { path: 'book-return', component: BookReturnComponent },
  { path: 'newreservations', component: NotReturnedBooksComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
