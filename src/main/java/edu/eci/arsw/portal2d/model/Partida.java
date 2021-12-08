package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.MapaDto;
import edu.eci.arsw.portal2d.dto.PlayerDto;
import edu.eci.arsw.portal2d.dto.Punto;

import java.util.HashMap;

public class Partida {

    private Integer duration;
    private MapaDto mapa;
    private Integer podio = 0;
    private Integer oro = 100;
    private Integer experiencia = 100;
    private final HashMap<String, PlayerDto> players = new HashMap<>();


    public Partida() {
        this.mapa = new MapaDto(new Punto(0,468), new Punto(920,468));
    }

    public HashMap<String, PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(String idSala, PlayerDto player){
        players.put(idSala, player);
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public MapaDto getMapa() {
        return mapa;
    }

    public void setMapa(MapaDto mapa) {
        this.mapa = mapa;
    }

    public Integer getPodio() {
        return podio;
    }

    public void setPodio() {
        this.podio += 1;
    }

    public Integer getOro() {
        return oro;
    }

    public void setOro(Integer oro) {
        this.oro = oro;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }
}
