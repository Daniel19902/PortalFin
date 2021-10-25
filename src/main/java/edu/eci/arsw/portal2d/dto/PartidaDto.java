package edu.eci.arsw.portal2d.dto;

import edu.eci.arsw.portal2d.model.Mapa;

public class PartidaDto {

    private Integer duration;
    private edu.eci.arsw.portal2d.model.Mapa mapa;
    private Integer podio = 0;


    public PartidaDto() {
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public edu.eci.arsw.portal2d.model.Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Integer getPodio() {
        return podio;
    }

    public void setPodio(Integer podio) {
        this.podio = podio;
    }

}
