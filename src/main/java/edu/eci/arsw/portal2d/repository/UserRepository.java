package edu.eci.arsw.portal2d.repository;

import edu.eci.arsw.portal2d.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {

    public Optional<User> findUserByMail(String mail);


}
