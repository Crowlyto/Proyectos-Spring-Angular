/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Orden;
import com.ecommerce.repositorio.IOrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class OrdenServicioImpl implements IOrdenServicio {
    @Autowired
    private IOrdenRepositorio repoO;
    @Override
    public Orden save(Orden orden) {
        return repoO.save(orden);

    }

}
