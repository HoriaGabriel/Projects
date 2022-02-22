package library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.AuthorService;
import library.model.Author;
import library.validator.AuthorValidator;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorValidator authorValidator;

    @PostMapping("/author")
    public Author save(Author author) {
        authorValidator.validate(author);
        return authorService.save(author);
    }

    public void  validate(Author author){
        authorValidator.validate(author);
    }


    @GetMapping("/author")
    public List<Author> findAll() {
        return authorService.findAll();
    }

}
