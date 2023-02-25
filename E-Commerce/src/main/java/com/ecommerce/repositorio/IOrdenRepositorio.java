/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.repositorio;

import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author crowl
 */
@Repository
public interface IOrdenRepositorio extends JpaRepository<Orden, Integer>{
    
    List<Orden>findByUsuario(Usuario usuario);
    
}
