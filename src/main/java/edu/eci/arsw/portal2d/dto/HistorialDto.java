package edu.eci.arsw.portal2d.dto;



public class HistorialDto {

    private String id;
    private String nombre = "";
    private int podio;
    private int oro;
    private int experiencia;


    public HistorialDto(String id, int podio, int oro, int experiencia) {
        this.id = id;
        this.podio = podio;
        this.oro = oro;
        this.experiencia = experiencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPodio() {
        return podio;
    }

    public void setPodio(int podio) {
        this.podio = podio;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
