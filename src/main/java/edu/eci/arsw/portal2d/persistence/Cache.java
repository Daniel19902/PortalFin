package edu.eci.arsw.portal2d.persistence;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.PlayerDto;
import edu.eci.arsw.portal2d.model.Partida;
import edu.eci.arsw.portal2d.model.Player;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;

import java.util.List;

public interface Cache {

    public User getUser();

    public void setUser(User user);

    public Sala getSala();

    public void setSala(Sala sala);

    void crearJugadores(String idSala, List<String> numeroJugadores);

    List<Player> getPlayers(String idSala);

    void updatePlayer(String idSala, Player player);

    void almacenarPartida(String idSala);

    boolean finPartida(Player player);

    HistorialDto infoPartida(String idSala, String idUser);

}
