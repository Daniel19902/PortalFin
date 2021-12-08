package edu.eci.arsw.portal2d.dto;

public class PlayerDto {


    private String idSala;
    private String name;
    private int x = 0;
    private int y = 0;
    private int oro = 0;
    private int expe = 0;
    private int lugar = 0;
    private int podio = 0;

    public  PlayerDto(){

    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getExpe() {
        return expe;
    }

    public void setExpe(int expe) {
        this.expe = expe;
    }

    public PlayerDto(String id) {
        this.name = id;
    }

    public int getPodio() {
        return podio;
    }

    public void setPodio(int podio) {
        this.podio = podio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }
}
