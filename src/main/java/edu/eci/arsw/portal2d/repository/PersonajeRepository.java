package edu.eci.arsw.portal2d.repository;

import edu.eci.arsw.portal2d.model.Personaje;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonajeRepository extends CrudRepository<Personaje, String> {


    Optional<Personaje> findPersonajeByNombre(String name);

}
