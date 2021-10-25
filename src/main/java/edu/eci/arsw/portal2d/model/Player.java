package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.PlayerDto;

public class Player {
    private String idSala;
    private String id;
    private int x = 0;
    private int y = 0;
    private int lugar = 0;

    public Player(){

    }

    public Player(PlayerDto playerDto) {
        this.idSala = playerDto.getIdSala();
        this.id = playerDto.getId();
        this.x = playerDto.getX();
        this.y = playerDto.getY();
        this.lugar = playerDto.getLugar();
    }

    public Player(String idPlayer, String idSala){
        this.id = idPlayer;
        this.idSala = idSala;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
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
}
