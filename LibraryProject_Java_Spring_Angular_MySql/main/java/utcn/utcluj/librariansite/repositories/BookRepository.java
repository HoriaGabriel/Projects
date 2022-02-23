package utcn.utcluj.librariansite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.utcluj.librariansite.models.Author;
import utcn.utcluj.librariansite.models.Books;
import utcn.utcluj.librariansite.models.Genre;

public interface BookRepository extends JpaRepository<Books, Long> {

    List<Books> findByNameContaining(String name);
    List<Books> findByGenre(Genre g);
    List<Books> findByAuthor(Author a);
    List<Books> findByName(String name);

}
