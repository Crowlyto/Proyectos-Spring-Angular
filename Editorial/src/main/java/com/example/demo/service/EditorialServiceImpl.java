/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.clientefeing.AutorCliente;
import com.example.demo.models.Autor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EditorAwareTag;

/**
 *
 * @author crowl
 */
@Service
public class EditorialServiceImpl implements IEditorialService{

    @Autowired
    private AutorCliente autor;
    @Override
    public List<Autor> listar() {
        
       return autor.listar();
    }
    
}
