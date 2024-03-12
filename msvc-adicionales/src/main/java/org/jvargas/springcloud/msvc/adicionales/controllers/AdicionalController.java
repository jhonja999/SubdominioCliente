package org.jvargas.springcloud.msvc.adicionales.controllers;

import jakarta.validation.Valid;
import org.jvargas.springcloud.msvc.adicionales.models.entity.Adicional;
import org.jvargas.springcloud.msvc.adicionales.services.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/adicional")
public class AdicionalController {

    @Autowired
    private AdicionalService service;

    @GetMapping
    public List<Adicional> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Adicional> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Adicional servicioAdicional, BindingResult result) {
        if(service.porDni(servicioAdicional.getDni()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Este cliente ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(servicioAdicional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Adicional empleado,BindingResult result,  @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<Adicional> op = service.porId(id);
        if (op.isPresent()) {
            Adicional adicionalDB = op.get();

            if(!empleado.getDni().equalsIgnoreCase(adicionalDB.getDni()) && service.porDni(empleado.getDni()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Upss!!! ", "Ya existe el Dni en otro cliente"));
            }
            adicionalDB.setNombre(empleado.getNombre());
            adicionalDB.setPrecio(empleado.getPrecio());
            adicionalDB.setDni(empleado.getDni());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(adicionalDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Adicional> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error!!! " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
