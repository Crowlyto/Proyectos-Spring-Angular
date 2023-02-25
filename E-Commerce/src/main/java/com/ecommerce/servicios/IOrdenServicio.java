/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Usuario;
import java.util.List;

/**
 *
 * @author crowl
 */
public interface IOrdenServicio {
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden>findByUsuario(Usuario usuario);
    
}
