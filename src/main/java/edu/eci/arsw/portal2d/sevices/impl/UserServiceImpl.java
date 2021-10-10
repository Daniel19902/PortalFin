package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.repository.UserByRolRepository;
import edu.eci.arsw.portal2d.repository.UserRepository;
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
    private UserByRolRepository userByRolRepository;

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
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
