/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Usuario;
import com.ecommerce.repositorio.IUsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class UsuarioServicioImpl implements IUsuarioServicio{
    @Autowired
    private IUsuarioRepositorio repoU;

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repoU.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
    return repoU.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repoU.findByEmail(email);
    }
    
    
    
}
