/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ensalada.service;

import com.ensalada.model.Usuario;
import com.ensalada.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author crowl
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly= true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
     return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly= true)
    public Optional<Usuario> findById(Integer id) {
       return usuarioRepository.findById(id);
    }
    
    
}
