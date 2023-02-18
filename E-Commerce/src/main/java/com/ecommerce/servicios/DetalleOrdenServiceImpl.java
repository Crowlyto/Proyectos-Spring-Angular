/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.DetalleOrden;
import com.ecommerce.repositorio.IDetalleOrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenServicio{

    @Autowired
    private IDetalleOrdenRepositorio repoD;
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
       return repoD.save(detalleOrden);
    }
    
}
