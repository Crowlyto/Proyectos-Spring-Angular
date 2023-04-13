/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Orden;
import com.ensalada.repository.IOrdenRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author crowl
 */
@Service
public class OrdenServiceImpl implements IOrdenService{
    @Autowired
    private IOrdenRepository ordenRepositorio;

    @Override
    public List<Orden> findAll() {
        return (List<Orden>) ordenRepositorio.findAll();
     }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepositorio.findById(id);
     }

    @Override
    public Orden save(Orden orden) {
        return ordenRepositorio.save(orden);
     }

    @Override
    public void deleteById(Integer id) {
        ordenRepositorio.deleteById(id);
     }
    
}
