package edu.eci.arsw.portal2d.persistence;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.dto.PlayerDto;
import edu.eci.arsw.portal2d.model.Partida;
import edu.eci.arsw.portal2d.model.Player;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;

import java.util.LinkedList;
import java.util.List;

public interface Cache {

    public User getUser();

    public void setUser(User user);

    public Sala getSala();

    public void setSala(Sala sala);

    void crearJugadores(String idSala, List<String> numeroJugadores);

    List<PlayerDto> players(String idSala);

    void updatePlayer(String idSala, Player player);

    void almacenarPartida(String idSala, String idUser);

    boolean finPartida(String idSala, String namePlayer);

    List<PlayerDto> iniciarPartida(String idSala);

    HistorialDto infoPartida(String idSala, String idUser);

    void crearJugador(String idSala, String name);

    LinkedList<PlayerDto> moverPlayer(int x, int y, String name, String idSala);

    List<PlayerDto> infoPodioPlayers(String idSala);
}
