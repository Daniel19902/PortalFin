package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.persistence.Cache;
import edu.eci.arsw.portal2d.repository.UserRepository;
import edu.eci.arsw.portal2d.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cache cache;


    @Override
    public User save(UserDto userDto) {
        return userRepository.save(new User(userDto));
    }

    public Optional<User> verificarMail(String mail, String password){
        return userRepository.findUserByMail(mail);
    }

    public boolean verificarPassword(String password, User user){
        return Objects.equals(user.getPassword(), password);
    }

    @Override
    public void asignarSala(String idUser, String salaID) {
        User user = userRepository.findById(idUser).get();
        user.setIdSala(salaID);
        userRepository.save(user);
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
        List<String> idPlayers = new LinkedList<>();
        List<User> players =  (List<User>) userRepository.findByIdSala(idSala);
        for(User u: players){
            idPlayers.add(u.getId());
        }
        return idPlayers;
    }

}
