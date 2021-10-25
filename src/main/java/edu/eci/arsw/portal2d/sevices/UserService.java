package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto userDto);

    List<User> getUsers();

    Optional<User> getUser(String idUser);

    Optional<User> verificarMail(String mail,String password);

    boolean verificarPassword(String password, User user);

    void asignarSala(String idUser, String salaID);

    void cacheUser(User user);

    User getCacheUser();

    List<String> findPlayers(String idSala);

}
