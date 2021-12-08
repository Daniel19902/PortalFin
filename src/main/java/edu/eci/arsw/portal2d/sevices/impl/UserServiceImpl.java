package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.Personaje;
import edu.eci.arsw.portal2d.model.Player;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.persistence.Cache;
import edu.eci.arsw.portal2d.repository.PersonajeRepository;
import edu.eci.arsw.portal2d.repository.UserRepository;
import edu.eci.arsw.portal2d.repository.UserServiceException;
import edu.eci.arsw.portal2d.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cache cache;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public User save(UserDto userDto) throws UserServiceException {
        if(!userRepository.findUserByName(userDto.getName()).isPresent()){
            return userRepository.save(new User(userDto));
        }
        throw new UserServiceException();
    }

    public Optional<User> verificarName(String name) throws UserServiceException {
        if(userRepository.findUserByName(name).isPresent()){
            return userRepository.findUserByName(name);
        }
        throw new UserServiceException();
    }

    public boolean verificarPassword(String password, User user){
        return Objects.equals(user.getPassword(), password);
    }

    @Override
    public void asignarSala(String idUser, String salaID) {
        Personaje user = personajeRepository.findById(idUser).get();
        user.setIdSala(salaID);
        personajeRepository.save(user);
    }

    @Override
    public void cacheUser(User user) {
        cache.setUser(user);
    }

    @Override
    public User getCacheUser() {
        return cache.getUser();
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public List<String> findPlayers(String idSala) {
        /*
        List<String> idPlayers = new LinkedList<>();
        List<User> players =  (List<User>) userRepository.findByIdSala(idSala);
        for(User u: players){
            idPlayers.add(u.getId());
        }
        return idPlayers;

         */
        return null;
    }

    @Override
    public Optional<Personaje> getPersonaje(String idUser) {
        return personajeRepository.findById(idUser);
    }


}
