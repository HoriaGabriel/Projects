package utcn.utcluj.librariansite.payload.request;

import utcn.utcluj.librariansite.models.Books;

public class ReservationRequest {

    private String user;
    private Books book;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}
