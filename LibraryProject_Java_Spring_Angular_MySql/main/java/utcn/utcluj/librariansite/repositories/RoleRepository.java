package utcn.utcluj.librariansite.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utcn.utcluj.librariansite.models.ERole;
import utcn.utcluj.librariansite.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}

