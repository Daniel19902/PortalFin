package edu.eci.arsw.portal2d.model;

import edu.eci.arsw.portal2d.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "USUARIO")
public class User {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "CORREO")
    private String mail;
    @Column(name = "CONTRASENA")
    private String password;
    @Column(name = "EDAD")
    private int age;
    @Column(name = "IDSALA")
    private String idSala;

    public User(UserDto userDto) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.name = userDto.getName();
        this.mail = userDto.getMail();
        this.password = userDto.getPassword();
        this.age = userDto.getAge();
        this.idSala = getIdSala();
    }

    public User() {
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
