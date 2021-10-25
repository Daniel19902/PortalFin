package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.SalaDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "SALA")
public class Sala {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CODIGOSALA")
    private String codigoSala;
    @Column(name = "NUMEROUSERS")
    private int numeroUsers;


    public Sala(SalaDto salaDto) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.codigoSala = uuid.toString();
        this.nombre = salaDto.getNombre();
        this.numeroUsers = salaDto.getNumeroUsers();
    }

    public Sala() {

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

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public int getNumeroUsers() {
        return numeroUsers;
    }

    public void setNumeroUsers(int numeroUsers) {
        this.numeroUsers = numeroUsers;
    }
}
