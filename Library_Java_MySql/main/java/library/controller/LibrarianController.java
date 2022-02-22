package library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.LibrarianService;
import library.model.Librarian;
import library.validator.LibrarianValidator;


@RestController
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private LibrarianValidator librarianValidator;

    @PostMapping("/librarians")
    public Librarian save(Librarian librarian) {
        librarianValidator.validate(librarian);
        return librarianService.save(librarian);
    }

    public void  validate(Librarian librarian){
        librarianValidator.validate(librarian);
    }


    @GetMapping("/librarians")
    public List<Librarian> findAll() {
        return librarianService.findAll();
    }

}
