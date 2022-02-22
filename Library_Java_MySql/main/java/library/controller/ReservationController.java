package library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.ReservationService;
import library.model.Reservation;
import library.validator.ReservationValidator;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationValidator reservationValidator;

    @PostMapping("/reservation")
    public Reservation save(Reservation reservation) {
        reservationValidator.validate(reservation);
        return reservationService.save(reservation);
    }

    public void  validate(Reservation reservation){
        reservationValidator.validate(reservation);
    }


    @GetMapping("/reservation")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

}
