package library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table (name="reservations")
public class Reservation extends AbstractEntity {

    @Column(name="BookID")
    private Long bookID;

    @Column(name="MemberID")
    private Long memberID;

    @Column(name="ReturnDate")
    private LocalDate returnDate;

    @Column(name="Status")
    private String status;

    @Column(name="Overdue")
    private String overdue;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {

        this.returnDate = LocalDate.now().plus(2, ChronoUnit.WEEKS);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }
}
