/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Hola.demo.service;

import Hola.demo.models.Autor;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IAutorService {
     public List<Autor> findAll();
    public Optional<Autor> findById(Integer id);
    public Autor save(Autor autor);
    public void deleteById(Integer id);
}
