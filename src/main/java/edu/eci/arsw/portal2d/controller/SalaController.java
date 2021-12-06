package edu.eci.arsw.portal2d.controller;


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


    @MessageMapping("/newUser.{numUser}")
    public synchronized void updateUser(@DestinationVariable String numUser, String namePlayer){
        System.out.println("New user: "+numUser);
        cache.crearJugador(numUser, namePlayer);
        messagingTemplate.convertAndSend("/topic/newUser."+numUser, cache.players(numUser));
    }

    @MessageMapping("/iniciarPartida.{idSala}")
    public void iniciarPartida(@DestinationVariable String idSala){
        System.out.println(idSala);
        messagingTemplate.convertAndSend("/topic/iniciarPartida."+idSala, true);
        //messagingTemplate.convertAndSend("/topic/paintPlayers."+idSala, cache.iniciarPartida(idSala));
    }

    @MessageMapping("/paintPlayers.{idSala}")
    public void paintPlayers(@DestinationVariable String idSala){
        System.out.println(idSala);
        messagingTemplate.convertAndSend("/topic/paintPlayers."+idSala, cache.iniciarPartida(idSala));
    }

    @MessageMapping("/moverPersonaje.{idSala}/{x}/{y}")
    public void movePlayer(@DestinationVariable String idSala,@DestinationVariable float x, @DestinationVariable float y, String  namePlayer){
        /*
        if(cache.finPartida(player)){
            messagingTemplate.convertAndSend("/topic/finPartida."+idSala, player);
        }
        cache.updatePlayer(idSala, player);
        */
        System.out.println(x+" "+y+" "+namePlayer);
        messagingTemplate.convertAndSend("/topic/paintPlayer."+idSala, cache.moverPlayer((int)x,(int)y,namePlayer,idSala));
    }

    @MessageMapping("/infoPlayer.{idSala}/{idUser}")
    public void infoPlayer(@DestinationVariable String idSala, @DestinationVariable String idUser){
        messagingTemplate.convertAndSend("/topic/infoPlayer."+idSala, cache.infoPartida(idSala, idUser));
    }

    @MessageMapping("/mensaje.{idSala}")
    public void  mensaje(@DestinationVariable String idSala, String mensaje){
        System.out.println(mensaje);
        messagingTemplate.convertAndSend("/topic/mensaje."+idSala, mensaje);
    }

    public List<String> buscarJugadores(String idSala){
        return userService.findPlayers(idSala);
    }

}
