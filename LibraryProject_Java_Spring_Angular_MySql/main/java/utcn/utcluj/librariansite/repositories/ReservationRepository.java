package utcn.utcluj.librariansite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.utcluj.librariansite.models.Books;
import utcn.utcluj.librariansite.models.Reservation;
import utcn.utcluj.librariansite.models.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User u);
    List<Reservation> findByUserAndBook(User u, Books b);
    List<Reservation> findByUserAndStatus(User u,boolean b);
}
