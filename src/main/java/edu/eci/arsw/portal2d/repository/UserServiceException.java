package edu.eci.arsw.portal2d.repository;

public class UserServiceException extends Exception{

    private final String userNoRegistrado = "EL USUARIO NO SE ENCUENTRA REGISTRADO";
    private final String badPassword = "LA CONTRASEÑA NO SE CORRECTA";

    public  UserServiceException(){

    }

    public String loginServiceException(){
        return userNoRegistrado;
    }

    public String getBadPassword() {
        return badPassword;
    }
}
