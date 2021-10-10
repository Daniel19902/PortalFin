package edu.eci.arsw.portal2d.repository;

import edu.eci.arsw.portal2d.model.UserByRol;
import edu.eci.arsw.portal2d.model.UserByRolPK;
import org.springframework.data.repository.CrudRepository;

public interface UserByRolRepository extends CrudRepository<UserByRol, UserByRolPK> {
}
