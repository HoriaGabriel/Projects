package utcn.utcluj.librariansite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.utcluj.librariansite.models.Author;
import utcn.utcluj.librariansite.models.Genre;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContaining(String name);
    List<Author> findByName(String name);
}
