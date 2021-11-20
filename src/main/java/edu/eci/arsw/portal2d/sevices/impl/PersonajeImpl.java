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

    @Override
    public void savePersonaje(String id, String name) {
        PersonajeDto personajeDto = new PersonajeDto(id, name);
        personajeRepository.save(new Personaje(personajeDto));
    }

    @Override
    public Optional<Personaje> getPersonaje(String id) {
        return personajeRepository.findById(id);
    }
}
