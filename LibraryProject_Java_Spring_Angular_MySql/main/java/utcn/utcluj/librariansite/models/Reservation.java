package utcn.utcluj.librariansite.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Books book;

    public Reservation(boolean status, User user, Books book) {

        this.status = status;
        this.user = user;
        this.book = book;
    }

    public Reservation() {

        this.status = status;
        this.user = user;
        this.book = book;
    }

    public String getUser() {
        return user.getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBook() {
        return book.getName();
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
