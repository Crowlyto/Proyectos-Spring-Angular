/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.controller;

import com.ensalada.model.Producto;
import com.ensalada.service.IProductoService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(productoService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Producto producto, @PathVariable Integer id){
        Optional<Producto>produc=productoService.findById(id);
        if(produc.isPresent()){
            Producto aux=produc.get();
            aux.setName(producto.getName());
            aux.setCant(producto.getCant());
            aux.setDescription(producto.getDescription());
            aux.setPrice(producto.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(aux));
        }
        return ResponseEntity.notFound().build();
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
