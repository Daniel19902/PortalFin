package edu.eci.arsw.portal2d.model;


import edu.eci.arsw.portal2d.dto.PersonajeDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONAJE")
public class Personaje {
    @Id
    @Column(name = "IDUSUARIO")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "oro")
    private int oro;
    @Column(name = "experiencia")
    private int experiencia;
    @Column(name = "idsala")
    private String idSala;
    @Column(name = "nivel")
    private int nivel;

    public Personaje(PersonajeDto personajeDto){
        this.id = personajeDto.getId();
        this.nombre = personajeDto.getNombre();
        this.oro = personajeDto.getOro();
        this.experiencia = personajeDto.getExperiencia();
        this.idSala = personajeDto.getIdSala();
        this.nivel = personajeDto.getNivel();
    }

    public Personaje(){

    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel += nivel;
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

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro += oro;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia += experiencia;
    }

    public void setExperienciaNivel(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }
}
