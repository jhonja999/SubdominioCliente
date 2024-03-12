package org.jvargas.springcloud.msvc.adicionales.services;

import org.jvargas.springcloud.msvc.adicionales.models.entity.Adicional;


import java.util.*;

public interface AdicionalService {
    List<Adicional> listar(); //lista  user
    Optional<Adicional> porId(Long id); //te devuelve un solo valor
    Adicional guardar(Adicional empleado); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Adicional> porDni(String dni);
}