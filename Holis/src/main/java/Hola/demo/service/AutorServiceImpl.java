/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hola.demo.service;

import Hola.demo.models.Autor;
import Hola.demo.repository.AutorRepositorio;
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
public class AutorServiceImpl implements IAutorService{
    @Autowired
    AutorRepositorio repoA;
    @Transactional(readOnly = true)
    @Override
    public List<Autor> findAll() {
        return repoA.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Autor> findById(Integer id) {
        return repoA.findById(id);
    }
    @Transactional
    @Override
    public Autor save(Autor autor) {
        return repoA.save(autor);
    }
    @Transactional
    @Override
    public void deleteById(Integer id) {
        repoA.deleteById(id);

    }
}
