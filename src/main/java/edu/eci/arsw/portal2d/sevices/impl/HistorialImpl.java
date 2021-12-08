package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.model.Historial;
import edu.eci.arsw.portal2d.repository.HistorialRepository;
import edu.eci.arsw.portal2d.sevices.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HistorialImpl implements HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    @Override
    public Historial setHistorial(HistorialDto historialDto) {
        return historialRepository.save(new Historial(historialDto));
    }

    @Override
    public Iterable<Historial> getHistorial(String name) {
        return historialRepository.findHistorialByNombre(name);
    }

}
