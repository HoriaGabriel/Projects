package utcn.utcluj.librariansite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.utcluj.librariansite.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findByNameContaining(String name);
}
