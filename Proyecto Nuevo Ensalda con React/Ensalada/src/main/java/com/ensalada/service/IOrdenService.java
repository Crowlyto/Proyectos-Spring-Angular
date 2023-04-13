/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Orden;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IOrdenService {

    public List<Orden> findAll();

    public Optional<Orden> findById(Integer id);

    public Orden save(Orden orden);

    public void deleteById(Integer id);
}
