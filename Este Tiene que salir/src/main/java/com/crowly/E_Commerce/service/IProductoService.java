/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.crowly.E_Commerce.service;

import com.crowly.E_Commerce.model.Producto;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author crowl
 */
public interface IProductoService {
    public List<Producto> findAll();
    public Page<Producto> findAll(Pageable pageable);
    public Optional<Producto> findById(Integer id);
    public void  delete(Integer id);
    public Producto save(Producto producto);
    
    
}
