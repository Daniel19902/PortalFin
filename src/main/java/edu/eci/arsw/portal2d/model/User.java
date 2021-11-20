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

    public User(UserDto userDto) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.name = userDto.getName();
        this.mail = userDto.getMail();
        this.password = userDto.getPassword();
    }

    public User() {
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

}
