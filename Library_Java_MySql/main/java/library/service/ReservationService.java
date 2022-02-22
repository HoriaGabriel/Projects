package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Reservation;
import library.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        Iterable<Reservation> all = reservationRepository.findAll();
        all.forEach(reservations::add);
        return reservations;
    }

}
