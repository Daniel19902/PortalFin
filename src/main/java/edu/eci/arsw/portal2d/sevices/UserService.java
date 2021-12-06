package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.Personaje;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.repository.UserServiceException;


import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto userDto) throws UserServiceException;

    List<User> getUsers();

    Optional<User> getUser(String idUser);

    Optional<User> verificarName(String name) throws UserServiceException;

    boolean verificarPassword(String password, User user);

    void asignarSala(String idUser, String salaID);

    void cacheUser(User user);

    User getCacheUser();

    List<String> findPlayers(String idSala);

    Optional<Personaje> getPersonaje(String idUser);
}
