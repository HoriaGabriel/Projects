package library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="books")
public class Book extends AbstractEntity{

    @Column(name="Title")
    private String title;

    @Column(name="GenreID")
    private Long genre;

    @Column(name="PublisherID")
    private Long publisher;

    @Column(name="ISBN")
    private String ISBN;

    @Column(name="Available")
    private int Available;

    @Column(name="ShelfID")
    private Long Shelf;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getGenre() {
        return genre;
    }

    public void setGenre(Long genre) {
        this.genre = genre;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAvailable() {
        return Available;
    }

    public void setAvailable(int available) {
        Available = available;
    }

    public Long getShelf() {
        return Shelf;
    }

    public void setShelf(Long shelf) {
        Shelf = shelf;
    }
}

