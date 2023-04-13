/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hola.demo.controller;

import Hola.demo.models.Autor;
import Hola.demo.service.IAutorService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crowl
 */
@RestController

public class AutorControlador {

    @Autowired
    private IAutorService serIA;

    private ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores= new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(),"El campo "+e.getField()+ " "+ e.getDefaultMessage());

        });
        return ResponseEntity.badRequest().body(errores);

    }

    @GetMapping
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok().body(serIA.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Integer id){
        Optional<Autor> autor= serIA.findById(id);
        if(autor.isPresent()){
            return ResponseEntity.ok().body(autor.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Autor autor, BindingResult result){
        if(result.hasErrors()){
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serIA.save(autor));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editar(@PathVariable Integer id, @Valid @RequestBody Autor autor, BindingResult result){
        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Autor> autor1= serIA.findById(id);
        if(autor1.isPresent()){
            Autor autorDB= autor1.get();
            autorDB.setNombre(autor.getNombre());
            serIA.save(autorDB);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorDB);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        Optional<Autor> autor=serIA.findById(id);
        if(autor.isEmpty()){
            serIA.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
