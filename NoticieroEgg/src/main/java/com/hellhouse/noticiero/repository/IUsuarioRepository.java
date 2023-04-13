/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hellhouse.noticiero.repository;

import com.hellhouse.noticiero.models.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author crowl
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    public Optional<Usuario> findByUsername(String username);
    
}
