package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.dto.SalaDto;
import edu.eci.arsw.portal2d.model.Sala;
import edu.eci.arsw.portal2d.model.User;

import java.util.List;
import java.util.Optional;

public interface SalaService {


    Sala crearSala(SalaDto salaDto);

    List<Sala> getSalas();

    Sala getSala(String idSala);

    void setSala(Sala Sala);

    Sala getSala();

}
