package utcn.utcluj.librariansite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.utcluj.librariansite.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByNameContaining(String name);
    List<Genre> findByName(String name);
}
