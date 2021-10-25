package edu.eci.arsw.portal2d.sevices;

import edu.eci.arsw.portal2d.dto.HistorialDto;
import edu.eci.arsw.portal2d.model.Historial;

public interface HistorialService {

    Historial setHistorial(HistorialDto historialDto);

    Iterable<Historial> getHistorial(String idUser);
}
