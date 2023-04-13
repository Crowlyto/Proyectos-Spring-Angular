/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Producto;
import com.ecommerce.servicios.IDetalleOrdenServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.servicios.IProductoServicio;
import com.ecommerce.servicios.IUsuarioServicio;
import com.ecommerce.servicios.IOrdenServicio;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private IProductoServicio servP;
    @Autowired
    private IUsuarioServicio servU;
    @Autowired
    private IOrdenServicio servO;
    
    private Logger log= LoggerFactory.getLogger(AdministradorController.class);
    
    
    
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=servP.findAll();
        model.addAttribute("productos",productos);
        
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", servU.findAll());
        
        return "administrador/usuarios";
    }
    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes", servO.findAll());
        return "administrador/ordenes";
    }
    @GetMapping("/detalles/{id}")
    public String detallesOrdenes(Model model, @PathVariable Integer id){
        log.info("Id de orden: {}", id);
        
        Orden orden=servO.findById(id).get();
        model.addAttribute("detalle", orden.getDetalle());
        
        return "administrador/detalleorden";
    }
     @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();

        model.addAttribute("producto", producto);
        return "administrador/productohome";
    }
    
    @PostMapping("/search")
    public String searchProducto(@RequestParam String busqueda, Model model){
        log.info("Nombre del Producto: {}", busqueda);
        List<Producto>productos=servP.findAll().
                stream().filter(p->p.getNombre().
                contains(busqueda)).
                collect(Collectors.toList());
        model.addAttribute("productos",productos);
        return "administrador/home";
    }
}
