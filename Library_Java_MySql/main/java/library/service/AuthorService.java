package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Author;
import library.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        Iterable<Author> all = authorRepository.findAll();
        all.forEach(authors::add);
        return authors;
    }
}
