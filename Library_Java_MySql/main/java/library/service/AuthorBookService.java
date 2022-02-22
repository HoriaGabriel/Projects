package library.service;

import java.util.ArrayList;
import java.util.List;

import library.model.Genre;
import library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.AuthorBook;
import library.repository.AuthorBookRepository;

@Service
public class AuthorBookService {

    @Autowired
    private AuthorBookRepository authorbookRepository;

    public AuthorBook save(AuthorBook authorbook) {
        return authorbookRepository.save(authorbook);
    }

    public List<AuthorBook> findAll() {
        List<AuthorBook> authorbooks = new ArrayList<>();
        Iterable<AuthorBook> all = authorbookRepository.findAll();
        all.forEach(authorbooks::add);
        return authorbooks;
    }
}
