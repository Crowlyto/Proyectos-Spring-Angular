/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Producto;
import com.ensalada.repository.IProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;
    

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
    return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
    productoRepository.deleteById(id);
    }
    
    
}
