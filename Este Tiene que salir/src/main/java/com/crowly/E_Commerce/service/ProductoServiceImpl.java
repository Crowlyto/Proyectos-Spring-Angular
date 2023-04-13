/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crowly.E_Commerce.service;

import com.crowly.E_Commerce.model.Producto;
import com.crowly.E_Commerce.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ProductoServiceImpl implements IProductoService{
@Autowired
private ProductoRepository productoRepo;
    @Override
    public List<Producto> findAll() {
    return (List<Producto>)productoRepo.findAll();
    }
    
    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepo.findAll(pageable);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
    return   (Optional<Producto>)productoRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        productoRepo.deleteById(id);
      }

    @Override
    public Producto save(Producto producto) {
        return productoRepo.save(producto);
    }

    
    
    
}
