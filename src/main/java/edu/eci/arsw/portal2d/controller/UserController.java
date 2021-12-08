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
        try{
            System.out.println(salaDto.getNombre()+" "+salaDto.getIDCreador());
            Sala sala = salaService.crearSala(salaDto);
            salaService.crearPartida(sala.getId(), sala.getIDCreador());
            userService.asignarSala(sala.getIDCreador(), sala.getId());
            return new ResponseEntity<>("Sala creada", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.OK);
        }
    }

    @GetMapping("/getUser/{idUser}")
    public ResponseEntity<?> getUser(@PathVariable String idUser){
        return new ResponseEntity<>(userService.getPersonaje(idUser).get(), HttpStatus.OK);
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
            return new ResponseEntity<>("LA CONTRASEÑA NO ES CORRECTA", HttpStatus.NOT_ACCEPTABLE);
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

    @GetMapping("/getHistorial/{name}")
    public ResponseEntity<?> getHistorialUser(@PathVariable String name){
        System.out.println(historialService.getHistorial(name));
        return new ResponseEntity(historialService.getHistorial(name),HttpStatus.OK);
    }

    @GetMapping("/podio/{idSala}")
    public ResponseEntity<?> getPodioPlayes(@PathVariable String idSala){
        return new ResponseEntity<>(salaService.infoPodioPlayers(idSala), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update/bonus/{name}/{oro}/{exp}")
    public ResponseEntity<?> updateBonus(@PathVariable String name, @PathVariable int oro, @PathVariable int exp){
        return new ResponseEntity<>(personajeService.upDateBonus(name, oro, exp), HttpStatus.ACCEPTED);
    }

}
