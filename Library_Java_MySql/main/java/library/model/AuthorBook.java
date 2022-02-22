package library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="authors_books")
public class AuthorBook extends AbstractEntity{

    @Column(name="booksID")
    private Long booksID;

    @Column(name="authorsID")
    private Long authorsID;

    public Long getBooksID() {
        return booksID;
    }

    public void setBooksID(Long booksID) {
        this.booksID = booksID;
    }

    public Long getAuthorsID() {
        return authorsID;
    }

    public void setAuthorsID(Long authorsID) {
        this.authorsID = authorsID;
    }
}
