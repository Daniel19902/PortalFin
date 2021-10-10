package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto userDto);

    List<User> getUsers();

    Optional<User> verificarMail(String mail,String password);

    boolean verificarPassword(String password, User user);

}
