/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.controller;

import com.ensalada.model.Usuario;
import com.ensalada.service.IUsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
//@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api/usuario")
public class UsuarioCotroller {
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<?> listar(){
       return ResponseEntity.ok().body(usuarioService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
        
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Integer id){
        Optional<Usuario>user =usuarioService.findById(id);
        if(user.isPresent()){
            Usuario aux=user.get();
            aux.setNombre(usuario.getNombre());
            aux.setDireccion(usuario.getDireccion());
            aux.setEmail(usuario.getEmail());
            aux.setTelefono(usuario.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }
    
}
