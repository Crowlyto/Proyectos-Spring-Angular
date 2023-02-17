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

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoServicio servP;

    //Almacenar detalles de la orden
    List<DetalleOrden> detalles = new ArrayList();
    //Datos de la orden
    Orden orden = new Orden();

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
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double total = 0;
        Optional<Producto> respuestaProducto = servP.get(id);
//        log.info("Producto añadido: {}", respuestaProducto.get());
//        log.info("cantidad: {}", cantidad);
        producto = respuestaProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        //Validar que no se añada dos veces el producto
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);
        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        total = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(total);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    //quitar producto del carrit
    @GetMapping("/delete/cart/{id}")
    public String deleteProducCart(@PathVariable Integer id, Model model) {
        //Lista nueva al momento de eliminar un producto
        List<DetalleOrden> ordenNueva = new ArrayList();
        for (DetalleOrden detalle : detalles) {
            if (detalle.getProducto().getId() != id) {
                ordenNueva.add(detalle);
            }
        }
        //Pongo la nueva lista con los productos restantes
        detalles = ordenNueva;

        double total = 0;
        total = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(total);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "/usuario/carrito";
    }
}
