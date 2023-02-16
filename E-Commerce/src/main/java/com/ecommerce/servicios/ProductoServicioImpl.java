/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Producto;
import com.ecommerce.repositorio.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepositorio repoP;

    @Override
    public Producto save(Producto producto) {
        
        return repoP.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        
        return repoP.findById(id);
    }

    @Override
    public void update(Producto producto) {
        
        repoP.save(producto);

    }

    @Override
    public void delete(Integer id) {
        
        repoP.deleteById(id);

    }

    @Override
    public List<Producto> findAll() {
       return repoP.findAll();
    }

}
