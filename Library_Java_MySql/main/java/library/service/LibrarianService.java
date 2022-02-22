package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Librarian;
import library.repository.LibrarianRepository;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    public Librarian save(Librarian person) {
        return librarianRepository.save(person);
    }

    public List<Librarian> findAll() {
        List<Librarian> librarians = new ArrayList<>();
        Iterable<Librarian> all = librarianRepository.findAll();
        all.forEach(librarians::add);
        return librarians;
    }

}
