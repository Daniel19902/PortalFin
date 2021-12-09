package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.PersonajeDto;
import edu.eci.arsw.portal2d.model.Personaje;
import edu.eci.arsw.portal2d.repository.PersonajeRepository;
import edu.eci.arsw.portal2d.sevices.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeImpl implements PersonajeService {

    @Autowired
    PersonajeRepository personajeRepository;

    private int numeroExp = 100;

    @Override
    public void savePersonaje(String id, String name) {
        PersonajeDto personajeDto = new PersonajeDto(id, name);
        personajeRepository.save(new Personaje(personajeDto));
    }

    @Override
    public Optional<Personaje> getPersonaje(String id) {
        return personajeRepository.findById(id);
    }

    @Override
    public Personaje upDateBonus(String name, int oro, int exp) {
        Personaje user = personajeRepository.findPersonajeByNombre(name).get();
        user.setOro(oro);
        user.setExperiencia(exp);
        updateNivel(user);
        personajeRepository.save(user);
        return  user;
    }

    @Override
    public int comprarSkin(int precio, String nombre) {
        Personaje personaje = personajeRepository.findPersonajeByNombre(nombre).get();
        if (personaje.getOro() >= precio) {
            personaje.setOro(-precio);
            personajeRepository.save(personaje);
            return personaje.getOro();
        }
        return -100;
    }

    public void updateNivel(Personaje user){
        if(user.getExperiencia() >= user.getNivel()*numeroExp){
            System.out.println(user.getNivel()*numeroExp-user.getExperiencia());
            user.setExperienciaNivel(user.getExperiencia()-user.getNivel()*numeroExp);
            user.setNivel(1);
            personajeRepository.save(user);
        }
    }
}
