/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IProductoService {
    public List<Producto> findAll();
    public Optional<Producto>findById(Integer id);
    public Producto save(Producto producto);
    public void deleteById(Integer id);
    
}
