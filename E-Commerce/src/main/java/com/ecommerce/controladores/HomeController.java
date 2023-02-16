/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductoServicio servP;
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos", servP.findAll());
        return "usuario/home";
    }
    
}
