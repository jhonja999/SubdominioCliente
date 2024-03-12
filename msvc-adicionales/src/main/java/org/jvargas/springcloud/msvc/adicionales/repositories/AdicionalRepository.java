package org.jvargas.springcloud.msvc.adicionales.repositories;

import org.jvargas.springcloud.msvc.adicionales.models.entity.Adicional;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface AdicionalRepository extends CrudRepository<Adicional, Long> {
    Optional<Adicional> findByDni(String dni);
}