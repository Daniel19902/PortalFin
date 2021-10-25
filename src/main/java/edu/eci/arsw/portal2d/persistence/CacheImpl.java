package edu.eci.arsw.portal2d.persistence;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.PlayerDto;
import edu.eci.arsw.portal2d.model.Partida;
import edu.eci.arsw.portal2d.model.Player;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class CacheImpl implements Cache{

    private User user = null;
    private Sala sala = null;

    private final HashMap<String, HashMap<String, Player>> players = new HashMap<>();
    private final HashMap<String, Partida> partida = new HashMap<>();


    public CacheImpl() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public void crearJugadores(String idSala, List<String> numeroJugadores) {
        HashMap<String, Player> players = new HashMap<>();
        for (String s: numeroJugadores){
            players.put(s, new Player(s, idSala));
        }
        this.players.put(idSala, players);
    }

    public boolean finPartida(Player player){
        Partida partidaPlayer = partida.get(player.getIdSala());
        if(player.getX() >= partidaPlayer.getMapa().getPuntoFin().getX()){
            partidaPlayer.setPodio(1);
            partidaPlayer.setPodioPlayers(player.getId(), partidaPlayer.getPodio());
        }
        return player.getX() >= partida.get(player.getIdSala()).getMapa().getPuntoFin().getX();
    }

    @Override
    public HistorialDto infoPartida(String idSala, String idUser) {
        System.out.println(partida.toString());
        Partida partidaUser = partida.get(idSala);
        HistorialDto historialDto = new HistorialDto(idUser, partidaUser.getPodioPlayers(idUser),0, 0);
        System.out.println(partidaUser.getPodioPlayers(idUser));
        System.out.println(historialDto);
        return historialDto;
    }

    @Override
    public void almacenarPartida(String idSala){
        partida.put(idSala, new Partida());
        System.out.println(idSala);
        System.out.println(partida);
    }

    @Override
    public List<Player> getPlayers(String idSala) {
        HashMap<String, Player> infoPlayer = players.get(idSala);
        Collection<Player> values = infoPlayer.values();
        return new LinkedList<>(values);
    }

    @Override
    public void updatePlayer(String idSala, Player player) {
        HashMap<String, Player> playerInfo = players.get(idSala);
        playerInfo.replace(player.getId(), player);
    }
}
