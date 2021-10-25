package edu.eci.arsw.portal2d.sevices.impl;

import edu.eci.arsw.portal2d.dto.SalaDto;
import edu.eci.arsw.portal2d.model.Sala;

import edu.eci.arsw.portal2d.model.User;
import edu.eci.arsw.portal2d.persistence.Cache;
import edu.eci.arsw.portal2d.repository.SalaRepository;
import edu.eci.arsw.portal2d.sevices.SalaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private Cache cache;

    @Override
    public Sala crearSala(SalaDto salaDto) {
        return salaRepository.save(new Sala(salaDto));
    }

    @Override
    public List<Sala> getSalas() {
        return (List<Sala>) salaRepository.findAll();
    }

    @Override
    public Sala getSala(String idSala) {
        return salaRepository.findById(idSala).get();
    }

    @Override
    public void setSala(Sala sala) {
        cache.setSala(sala);
    }

    @Override
    public Sala getSala() {
        return cache.getSala();
    }

}
