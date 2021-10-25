package edu.eci.arsw.portal2d.repository;

import edu.eci.arsw.portal2d.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {

    Optional<User> findUserByMail(String mail);

    Iterable<User> findByIdSala(String idSala);

}
