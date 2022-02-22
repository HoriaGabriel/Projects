package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Genre;
import library.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        Iterable<Genre> all = genreRepository.findAll();
        all.forEach(genres::add);
        return genres;
    }

}
