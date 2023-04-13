/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.controller;

import com.ensalada.model.Orden;
import com.ensalada.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/orden")
public class OrdenController {
    
    @Autowired
    private IOrdenService ordenServicio;
    
    @GetMapping
    public ResponseEntity<?> listar(){
        
        return ResponseEntity.ok().body(ordenServicio.findAll());
    }
    @PostMapping
    public ResponseEntity<?> guardar(Orden orden){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenServicio.save(orden));
    }
    
    
}
