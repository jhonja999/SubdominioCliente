package org.jvargas.springcloud.msvc.contactos.repostories;

import org.jvargas.springcloud.msvc.contactos.models.entity.Contacto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactoRepository extends CrudRepository<Contacto, Long> {
    Optional<Contacto> findByDni(String dni);
}
