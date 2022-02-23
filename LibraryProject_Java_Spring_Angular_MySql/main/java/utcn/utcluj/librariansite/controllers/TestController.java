package utcn.utcluj.librariansite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import utcn.utcluj.librariansite.models.Author;
import utcn.utcluj.librariansite.models.Books;
import utcn.utcluj.librariansite.models.Genre;
import utcn.utcluj.librariansite.models.Publisher;
import utcn.utcluj.librariansite.repositories.AuthorRepository;
import utcn.utcluj.librariansite.repositories.BookRepository;
import utcn.utcluj.librariansite.repositories.GenreRepository;
import utcn.utcluj.librariansite.repositories.PublisherRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Welcome To The Library";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Books>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Books> books = new ArrayList<Books>();

            if (title == null)
                bookRepository.findAll().forEach(books::add);
            else{
                bookRepository.findByNameContaining(title).forEach(books::add);

                if(books.isEmpty()){
                   List <Genre> g = genreRepository.findByName(title);
                   if(g.isEmpty()==false) {
                       bookRepository.findByGenre(g.get(0)).forEach(books::add);
                   }
                }

                if(books.isEmpty()){
                    System.out.println("ok");
                    List <Author> a = authorRepository.findByName(title);
                    if(a.isEmpty()==false) {
                        bookRepository.findByAuthor(a.get(0)).forEach(books::add);
                    }
                }

            }

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/librarian")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<List<Books>> getAllBooksForEditing(@RequestParam(required = false) String title) {

        try {
            List<Books> books = new ArrayList<Books>();

            if (title == null)
                bookRepository.findAll().forEach(books::add);
            else {
                bookRepository.findByNameContaining(title).forEach(books::add);

                if(books.isEmpty()){
                    List <Genre> g = genreRepository.findByName(title);
                    if(g.isEmpty()==false) {
                        bookRepository.findByGenre(g.get(0)).forEach(books::add);
                    }
                }

                if(books.isEmpty()){
                    List <Author> a = authorRepository.findByName(title);
                    if(a.isEmpty()==false) {
                        bookRepository.findByAuthor(a.get(0)).forEach(books::add);
                    }
                }
            }
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/librarian/books/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable("id") long id) {

        Optional<Books> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/reserve/{id}")
    public ResponseEntity<Books> getBookById2(@PathVariable("id") long id) {

        Optional<Books> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/librarian/books/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable("id") long id, @RequestBody Books book) {

        Optional<Books> bookData = bookRepository.findById(id);
        List<Genre> genreData = genreRepository.findByNameContaining(book.getGenre());
        List<Author> authorData = authorRepository.findByNameContaining(book.getAuthor());
        List<Publisher> publisherData = publisherRepository.findByNameContaining(book.getPublisher());


        if (bookData.isPresent()) {
            Books _book = bookData.get();
            _book.setName(book.getName());
            _book.setIsbn(book.getIsbn());
            _book.setAvailable(book.getAvailable());

            if(genreData.isEmpty()){
                Genre g = new Genre();
                g.setName(book.getGenre());
                genreRepository.save(g);
                _book.setGenre(g);
            }else{
                _book.setGenre(genreData.get(0));
            }

            if(authorData.isEmpty()){
                Author a = new Author();
                a.setName(book.getAuthor());
                authorRepository.save(a);
                _book.setAuthor(a);
            }else{
                _book.setAuthor(authorData.get(0));
            }

            if(publisherData.isEmpty()){
                Publisher p = new Publisher();
                p.setName(book.getPublisher());
                publisherRepository.save(p);
                _book.setPublisher(p);
            }else{
                _book.setPublisher(publisherData.get(0));
            }

            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/librarian/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-book")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Books> createBook(@RequestBody Books book) {

        System.out.println("ok435");

        if(bookRepository.findByName(book.getName()).isEmpty()){

            List<Genre> genreData = genreRepository.findByNameContaining(book.getGenre());
            List<Author> authorData = authorRepository.findByNameContaining(book.getAuthor());
            List<Publisher> publisherData = publisherRepository.findByNameContaining(book.getPublisher());

            Genre g1 = new Genre();
            Publisher p1 = new Publisher();
            Author a1 = new Author();


            if(genreData.isEmpty()){
                Genre g = new Genre();
                g.setName(book.getGenre());
                genreRepository.save(g);
                g1 = g;
            }else{
                g1 = genreData.get(0);
            }

            if(authorData.isEmpty()){
                Author a = new Author();
                a.setName(book.getAuthor());
                authorRepository.save(a);
                a1 = a;
            }else{
                a1 = authorData.get(0);
            }

            if(publisherData.isEmpty()){
                Publisher p = new Publisher();
                p.setName(book.getPublisher());
                publisherRepository.save(p);
                p1 = p;
            }else{
                p1 = publisherData.get(0);
            }

            try {
                Books _book = bookRepository
                        .save(new Books(book.getName(), book.getIsbn(), book.getAvailable(), g1, a1, p1));
                return new ResponseEntity<>(_book, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Books b = bookRepository.findByName(book.getName()).get(0);
        int av = book.getAvailable();
        int aux = b.getAvailable();
        int aux2 = aux + av;
        b.setAvailable(aux2);

        return new ResponseEntity<>(bookRepository.save(b), HttpStatus.OK);

    }
}
