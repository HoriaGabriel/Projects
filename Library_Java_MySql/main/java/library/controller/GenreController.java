package library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.GenreService;
import library.model.Genre;
import library.validator.GenreValidator;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreValidator genreValidator;

    @PostMapping("/genre")
    public Genre save(Genre genre) {
        genreValidator.validate(genre);
        return genreService.save(genre);
    }

    public void  validate(Genre genre){
        genreValidator.validate(genre);
    }


    @GetMapping("/genre")
    public List<Genre> findAll() {
        return genreService.findAll();
    }
}
