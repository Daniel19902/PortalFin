package edu.eci.arsw.portal2d.model;

import java.io.Serializable;
import java.util.Objects;

public class UserByRolPK implements Serializable {
    private int idRol;
    private String idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserByRolPK that = (UserByRolPK) o;
        return idRol == that.idRol && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idUser);
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
