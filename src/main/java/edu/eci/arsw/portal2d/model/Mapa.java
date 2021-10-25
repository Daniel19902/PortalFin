package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.Punto;

public class Mapa {

    private String mapa;
    private Punto puntoInicio;
    private Punto puntoFin;

    public Mapa() {
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public Punto getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(Punto puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public Punto getPuntoFin() {
        return puntoFin;
    }

    public void setPuntoFin(Punto puntoFin) {
        this.puntoFin = puntoFin;
    }
}

