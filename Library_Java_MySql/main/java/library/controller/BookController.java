package library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.BookService;
import library.model.Book;
import library.validator.BookValidator;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @PostMapping("/book")
    public Book save(Book book) {
        bookValidator.validate(book);
        return bookService.save(book);
    }

    /*public void  validate(Book book){
        bookValidator.validate(book);
    }*/

    @GetMapping("/book")
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
