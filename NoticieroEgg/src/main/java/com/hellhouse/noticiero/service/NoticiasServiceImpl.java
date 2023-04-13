/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.service;

import com.hellhouse.noticiero.models.Noticias;
import com.hellhouse.noticiero.repository.INoticiasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class NoticiasServiceImpl implements INoticiaService{
    
    @Autowired
    private INoticiasRepository repoN;

    @Override
    public List<Noticias> findAll() {
        return repoN.findAll();
    }

    @Override
    public Optional<Noticias> findById(Integer id) {
        return repoN.findById(id);
    }

    @Override
    public Optional<Noticias> findByTitulo(String titulo) {
        return repoN.findByTitulo(titulo);
    }

    @Override
    public Noticias save(Noticias noticia) {
        return repoN.save(noticia);
    }

    @Override
    public void deleteById(Integer id) {
        repoN.deleteById(id);
    }
    
}
