package edu.eci.arsw.portal2d.controller;


import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/portal")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity (userService.save(userDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{mail}/{password}")
    public ResponseEntity<?> loginUser(@PathVariable String mail, @PathVariable String password){
        Optional<User> user = userService.verificarMail(mail,password);
        if(userService.verificarPassword(password, user.get())){
            return new ResponseEntity<> (user, HttpStatus.OK);
        }
        return new ResponseEntity<>("La contraseña no es correcta",HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity (userService.getUsers(), HttpStatus.OK);
    }

}
