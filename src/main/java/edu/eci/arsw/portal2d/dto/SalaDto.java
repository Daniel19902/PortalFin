package edu.eci.arsw.portal2d.dto;


public class SalaDto {

    private String nombre;
    private int numeroUsers;
    private String IDCreador;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroUsers() {
        return numeroUsers;
    }

    public void setNumeroUsers(int numeroUsers) {
        this.numeroUsers = numeroUsers;
    }

    public String getIDCreador() {
        return IDCreador;
    }

    public void setIDCreador(String idCreador) {
        this.IDCreador = idCreador;
    }
}
