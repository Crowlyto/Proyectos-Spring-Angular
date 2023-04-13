/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.DetalleOrden;
import com.ensalada.repository.IDetalleOrdenRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public List<DetalleOrden> findAll() {
        return (List<DetalleOrden>) detalleOrdenRepository.findAll();
    }

    @Override
    public Optional<DetalleOrden> findById(Integer id) {
        return detalleOrdenRepository.findById(id);
    }

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

    @Override
    public void deleteById(Integer id) {
        detalleOrdenRepository.deleteById(id);
     }
    
}
