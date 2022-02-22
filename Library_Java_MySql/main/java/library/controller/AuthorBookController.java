package library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.AuthorBookService;
import library.model.AuthorBook;
import library.validator.AuthorBookValidator;

@RestController
public class AuthorBookController {

    @Autowired
    private AuthorBookService authorbookService;

    @Autowired
    private AuthorBookValidator authorbookValidator;

    @PostMapping("/authorbook")
    public AuthorBook save(AuthorBook authorbook) {
        authorbookValidator.validate(authorbook);
        return authorbookService.save(authorbook);
    }

    public void  validate(AuthorBook authorbook){
        authorbookValidator.validate(authorbook);
    }


    @GetMapping("/authorbook")
    public List<AuthorBook> findAll() {
        return authorbookService.findAll();
    }
}
