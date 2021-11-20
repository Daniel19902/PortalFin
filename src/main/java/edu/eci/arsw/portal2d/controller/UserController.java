package edu.eci.arsw.portal2d.controller;


import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.SalaDto;
import edu.eci.arsw.portal2d.dto.UserDto;
import edu.eci.arsw.portal2d.model.Personaje;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.repository.UserServiceException;
import edu.eci.arsw.portal2d.sevices.HistorialService;
import edu.eci.arsw.portal2d.sevices.PersonajeService;
import edu.eci.arsw.portal2d.sevices.SalaService;
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
    @Autowired
    private SalaService salaService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private PersonajeService personajeService;

    @PostMapping("/crearUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        try {
            User user = userService.save(userDto);
            personajeService.savePersonaje(user.getId(), user.getName());
            return new ResponseEntity<>("User creado", HttpStatus.CREATED);
        }catch (UserServiceException e){
            return new ResponseEntity<>("El nombre de usuario no esta permitido", HttpStatus.OK);
        }
    }

    @PostMapping("/sala")
    public ResponseEntity<?> createSala(@RequestBody SalaDto salaDto){
        return new ResponseEntity (salaService.crearSala(salaDto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "asignarSala/{idUser}/{idSala}")
    public ResponseEntity<?> asignarSala (@PathVariable String idUser,@PathVariable String idSala){
        userService.asignarSala(idUser, idSala);
        return new ResponseEntity ("User Asignado a la sala"+idSala, HttpStatus.OK);
    }

    @GetMapping(value = "login/{name}/{password}")
    public ResponseEntity<?> loginUser(@PathVariable String name, @PathVariable String password){
        try {
            Optional<User> user = userService.verificarName(name);
            if(userService.verificarPassword(password, user.get())){
                Optional<Personaje> personaje = personajeService.getPersonaje(user.get().getId());
                return new ResponseEntity<> (personaje, HttpStatus.OK);
            }
            return new ResponseEntity<>("LA CONTRASEÑA NO SE CORRECTA", HttpStatus.NOT_ACCEPTABLE);
        }catch (UserServiceException e){
            return new ResponseEntity<>(e.loginServiceException(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity (userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/salas")
    public ResponseEntity<?> getSalas(){
        return new ResponseEntity (salaService.getSalas(), HttpStatus.OK);
    }

    @PostMapping("/cacheUser")
    public ResponseEntity<?> cacheUser(@RequestBody User user){
        userService.cacheUser(user);
        return new ResponseEntity ("User guardado en cache", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCacheUser")
    public ResponseEntity<?> getCacheUser(){
        return new ResponseEntity (userService.getCacheUser(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/setIdSala/{idSala}")
    public ResponseEntity<?> setSala(@PathVariable String idSala){
        Sala sala = salaService.getSala(idSala);
        salaService.setSala(sala);
        return new ResponseEntity ("Sala guardada en cache", HttpStatus.OK);
    }

    @GetMapping("/getSalaUser")
    public ResponseEntity<?> getSala(){
        return new ResponseEntity (salaService.getSala(),HttpStatus.OK);
    }

    @PostMapping("/add/historial")
    public ResponseEntity<?> setHistorial(@RequestBody HistorialDto historialDto){

        return new ResponseEntity(historialService.setHistorial(historialDto), HttpStatus.CREATED);
    }

    @GetMapping("/getHistorial/{idUser}")
    public ResponseEntity<?> getHistorialUser(@PathVariable String idUser){
        System.out.println(historialService.getHistorial(idUser));
        return new ResponseEntity(historialService.getHistorial(idUser),HttpStatus.OK);
    }

}
