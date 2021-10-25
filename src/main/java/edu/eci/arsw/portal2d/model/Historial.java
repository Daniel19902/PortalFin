package edu.eci.arsw.portal2d.model;


import edu.eci.arsw.portal2d.dto.HistorialDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "IDuser")
    private String idUser;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "podio")
    private int podio;
    @Column(name = "oro")
    private int oro;
    @Column(name = "experiencia")
    private int experiencia;


    public Historial(HistorialDto historialDto) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.idUser = historialDto.getId();
        this.nombre = historialDto.getNombre();
        this.podio = historialDto.getPodio();
        this.oro = historialDto.getOro();
        this.experiencia = historialDto.getExperiencia();
    }

    public Historial() {

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
