package org.jvargas.springcloud.msvc.adicionales.services;

import org.jvargas.springcloud.msvc.adicionales.models.entity.Adicional;
import org.jvargas.springcloud.msvc.adicionales.repositories.AdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AdicionalServiceImpl implements AdicionalService {

    @Autowired //para inyectar la dependencia de una clase con metodos
    private AdicionalRepository repository;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Adicional> listar() {
        return (List<Adicional>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Adicional> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Adicional guardar(Adicional empleado) {
        return repository.save(empleado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Adicional> porDni(String dni) {return repository.findByDni(dni);}
}
