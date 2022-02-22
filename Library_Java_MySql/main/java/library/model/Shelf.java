package library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="shelf")
public class Shelf extends AbstractEntity{

    @Column(name="Shelf")
    private String shelf;

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}
