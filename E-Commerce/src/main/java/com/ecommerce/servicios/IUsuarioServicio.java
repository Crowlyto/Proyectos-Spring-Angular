/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Usuario;
import java.util.Optional;


/**
 *
 * @author crowl
 */

public interface IUsuarioServicio {
    Optional<Usuario> findById(Integer id);
    
}
