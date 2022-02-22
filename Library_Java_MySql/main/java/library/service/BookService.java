package library.service;

import java.util.ArrayList;
import java.util.List;

import library.model.Genre;
import library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Book;
import library.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Iterable<Book> all = bookRepository.findAll();
        all.forEach(books::add);
        return books;
    }

}
