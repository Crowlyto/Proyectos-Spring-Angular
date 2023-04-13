/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.models.Autor;
import com.example.demo.service.EditorialServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crowl
 */
@RestController
public class EditorialController {
    
    @Autowired
    private EditorialServiceImpl edito;
    
    @GetMapping("/lista")
    public ResponseEntity<?> listarAutores(){
        List<Autor> autores= edito.listar();
        return ResponseEntity.ok().body(autores);
    }
}
