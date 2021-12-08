package edu.eci.arsw.portal2d.repository;

import edu.eci.arsw.portal2d.model.Historial;
import org.springframework.data.repository.CrudRepository;

public interface HistorialRepository extends CrudRepository<Historial, String> {

    Iterable<Historial> findAllByIdUser(String idUser);

    Iterable<Historial> findHistorialByNombre(String nombre);

}
