import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardLibrarianComponent } from './board-librarian/board-librarian.component';
import { BoardCustomerComponent } from './board-customer/board-customer.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { BookDetailsComponent } from './book-details/book-details.component';
import { AddBookComponent } from './add-book/add-book.component';
import { ReservationDetailsComponent } from './reservation-details/reservation-details.component';
import { ReservationsListComponent } from './reservations-list/reservations-list.component';
import { BookReturnComponent } from './book-return/book-return.component';
import { NotReturnedBooksComponent } from './not-returned-books/not-returned-books.component';
import { SimpleimageComponent } from './simpleimage/simpleimage.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardLibrarianComponent,
    BoardCustomerComponent,
    BookDetailsComponent,
    AddBookComponent,
    ReservationDetailsComponent,
    ReservationsListComponent,
    BookReturnComponent,
    NotReturnedBooksComponent,
    SimpleimageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent, SimpleimageComponent]
})
export class AppModule { }
