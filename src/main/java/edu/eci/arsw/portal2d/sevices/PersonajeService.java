package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.model.Personaje;

import java.util.Optional;

public interface PersonajeService {

    void savePersonaje(String id, String name);

    Optional<Personaje> getPersonaje(String id);

    Personaje upDateBonus(String name, int oro, int exp);

}
