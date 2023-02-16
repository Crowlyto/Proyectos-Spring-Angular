/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.DetalleOrden;
import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Producto;
import com.ecommerce.servicios.ProductoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger log=LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoServicio servP;

    //Almacenar detalles de la orden
    List<DetalleOrden> detalles = new ArrayList();
    //Datos de la orden
    Orden orden=new Orden();

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", servP.findAll());
        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad) {
        DetalleOrden detalleOrden=new DetalleOrden();
        Producto producto=new Producto();
        double Total=0;
        Optional<Producto> respuestaProducto=servP.get(id);
        log.info("Producto añadido: {}", respuestaProducto.get());
        log.info("cantidad: {}", cantidad);

        return "usuario/carrito";
    }
}
