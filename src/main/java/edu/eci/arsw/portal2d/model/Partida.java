package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.MapaDto;
import edu.eci.arsw.portal2d.dto.Punto;

import java.util.HashMap;

public class Partida {

    private Integer duration;
    private MapaDto mapa;
    private Integer podio = 0;
    private Integer oro = 0;
    private Integer experiencia = 0;
    private final HashMap<String, Integer> podioPlayers = new HashMap<>();


    public Partida() {
        this.mapa = new MapaDto(new Punto(350,0), new Punto(960,0));
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

    public void setPodio(Integer podio) {
        this.podio += podio;
    }

    public Integer getOro() {
        return oro;
    }

    public void setOro(Integer oro) {
        this.oro = oro;
    }

    public int getPodioPlayers(String idUser) {
        return podioPlayers.get(idUser);
    }

    public void setPodioPlayers(String idPlayer, int podio) {
        this.podioPlayers.put(idPlayer,podio);
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }
}
