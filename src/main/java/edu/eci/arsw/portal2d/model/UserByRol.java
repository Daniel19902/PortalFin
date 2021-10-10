package edu.eci.arsw.portal2d.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "USARIOPORROL")
@IdClass(value = UserByRolPK.class)
public class UserByRol {
    @Id
    private int idRol;
    @Id
    private String idUser;

}
