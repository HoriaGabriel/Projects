package utcn.utcluj.librariansite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import utcn.utcluj.librariansite.models.*;
import utcn.utcluj.librariansite.payload.request.ReservationRequest;
import utcn.utcluj.librariansite.payload.request.ReturnRequest;
import utcn.utcluj.librariansite.repositories.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class ReservationController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/customer/reserve/{id}")
    public ResponseEntity<Reservation> createReservation(@PathVariable("id") long id, @Valid @RequestBody ReservationRequest req) {

        Optional<User> us = userRepository.findByUsername(req.getUser());

        List<Reservation> r1 = reservationRepository.findByUserAndStatus(us.get(),false);

        List<Reservation> r2 = reservationRepository.findByUserAndBook(us.get(),req.getBook());

        int aux=0;

        for(Reservation r3: r1){
            aux++;
        }

        for(Reservation r3: r2){

            if(r3.isStatus()==false){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        if(aux>=3){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Books b = req.getBook();

        List<Books> b1 = bookRepository.findByName(b.getName());
        Books b2 = b1.get(0);

        int auxili = b2.getAvailable();
        int auxili2 = auxili - 1;

        b2.setAvailable(auxili2);

        bookRepository.save(b2);

        Reservation r = new Reservation();
        r.setUser(us.get());
        r.setBook(b);
        r.setStatus(false);

        try {
              reservationRepository.save(r);
              return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations( @Valid @RequestParam String username) {

        try {

            Optional<User> u = userRepository.findByUsername(username);

            List<Reservation> reservations = new ArrayList<Reservation>();

            reservationRepository.findByUserAndStatus(u.get(),true).forEach(reservations::add);


            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservations, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/newreservations")
    public ResponseEntity<List<Reservation>> getAllReservations2( @Valid @RequestParam String username) {

        try {

            Optional<User> u = userRepository.findByUsername(username);

            List<Reservation> reservations = new ArrayList<Reservation>();

            reservationRepository.findByUserAndStatus(u.get(),false).forEach(reservations::add);


            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservations, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/return-book")
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReturnRequest returnRequest) {


      Optional<User> u = userRepository.findByUsername(returnRequest.getUser());
      List<Books> b = bookRepository.findByName(returnRequest.getBook());
      List<Reservation> r1 = reservationRepository.findByUserAndBook(u.get(), b.get(0));

      for(Reservation r: r1){
          r.setStatus(true);
      }

      return new ResponseEntity<>(reservationRepository.save(r1.get(0)), HttpStatus.OK);
    }
}
