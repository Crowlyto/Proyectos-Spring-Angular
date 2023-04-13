/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IUsuarioService {
    public List<Usuario> findAll();
    public Usuario save(Usuario usuario);
    public void deleteById(Integer id);
    public Optional<Usuario> findById(Integer id);
    
    
}
