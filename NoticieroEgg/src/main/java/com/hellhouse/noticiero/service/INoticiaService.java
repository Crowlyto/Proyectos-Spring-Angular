/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hellhouse.noticiero.service;

import com.hellhouse.noticiero.models.Noticias;
import com.hellhouse.noticiero.repository.INoticiasRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface INoticiaService{
    public List<Noticias> findAll();
    public Optional<Noticias> findById(Integer id);
    public Optional<Noticias> findByTitulo(String titulo);
    public Noticias save(Noticias noticia);
    public void deleteById(Integer id);
    
}
