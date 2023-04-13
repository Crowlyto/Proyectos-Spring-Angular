/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.clientefeing;

import com.example.demo.models.Autor;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author crowl
 */
@FeignClient(name = "msvc-autor")
public interface AutorCliente {
    
    @GetMapping
    public List<Autor> listar();
    
}
