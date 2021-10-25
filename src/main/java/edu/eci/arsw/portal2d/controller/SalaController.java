package edu.eci.arsw.portal2d.controller;


import edu.eci.arsw.portal2d.dto.PlayerDto;
import edu.eci.arsw.portal2d.model.Player;
import edu.eci.arsw.portal2d.persistence.Cache;
import edu.eci.arsw.portal2d.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class SalaController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    Cache cache;
    @Autowired
    UserService userService;


    private int numeroDeUser;


    @MessageMapping("/newUser.{numUser}")
    public synchronized void updateUser(@DestinationVariable String numUser){
        numeroDeUser+=1;
        System.out.println("New user: "+numeroDeUser);
        messagingTemplate.convertAndSend("/topic/newUser."+numUser, numeroDeUser);
    }

    @MessageMapping("/iniciarPartida.{idSala}")
    public void iniciarPartida(@DestinationVariable String idSala){
        cache.crearJugadores(idSala, buscarJugadores(idSala));
        cache.almacenarPartida(idSala);
        messagingTemplate.convertAndSend("/topic/paintPlayers."+idSala, cache.getPlayers(idSala));
        messagingTemplate.convertAndSend("/topic/iniciarPartida."+idSala, "Iniciar");
    }

    @MessageMapping("/movePlayer.{idSala}")
    public void movePlayer(@DestinationVariable String idSala, Player player){
        if(cache.finPartida(player)){
            messagingTemplate.convertAndSend("/topic/finPartida."+idSala, player);
        }
        cache.updatePlayer(idSala, player);
        messagingTemplate.convertAndSend("/topic/paintPlayers."+idSala, cache.getPlayers(idSala));
    }

    @MessageMapping("/infoPlayer.{idSala}/{idUser}")
    public void infoPlayer(@DestinationVariable String idSala, @DestinationVariable String idUser){
        messagingTemplate.convertAndSend("/topic/infoPlayer."+idSala, cache.infoPartida(idSala, idUser));
    }

    public List<String> buscarJugadores(String idSala){
        return userService.findPlayers(idSala);
    }

}
