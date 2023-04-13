/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ensalada.service;


import com.ensalada.model.DetalleOrden;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IDetalleOrdenService {
     public List<DetalleOrden> findAll();

    public Optional<DetalleOrden> findById(Integer id);

    public DetalleOrden save(DetalleOrden detalleOrden);

    public void deleteById(Integer id);
    
}
