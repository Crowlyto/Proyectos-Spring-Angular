/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.DetalleOrden;
import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Producto;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.IDetalleOrdenServicio;
import com.ecommerce.servicios.IOrdenServicio;
import com.ecommerce.servicios.IUsuarioServicio;
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
import com.ecommerce.servicios.IProductoServicio;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoServicio servP;
    @Autowired
    private IUsuarioServicio servU;
    @Autowired
    private IOrdenServicio servO;
    @Autowired
    private IDetalleOrdenServicio servD;

    //Almacenar detalles de la orden
    List<DetalleOrden> detalles = new ArrayList();
    //Datos de la orden
    Orden orden = new Orden();
    //El pedo esta por aca...!!!!
    

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        //log.info("Session usuario: {}", session.getAttribute("idUsuario"));
        model.addAttribute("productos", servP.findAll());
        //session
       // model.addAttribute("session",session.getAttribute("idUsuario"));
        
        
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
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        //session
        model.addAttribute("session",session.getAttribute("idUsuario"));
        return "/usuario/carrito";
    }
    
    @GetMapping("/order")
    public String order(Model model, HttpSession session){
        //Momentaneamente hasta tener usuarios, forma quemada
        Usuario usuario=servU.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        
        return "usuario/resumenorden";
    }
    
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion=new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(servO.generarNumeroOrden());
       
        Usuario usuario=servU.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        orden.setUsuario(usuario);
        servO.save(orden);
        //Guardar detalles
        for (DetalleOrden detalle : detalles) {
            detalle.setOrden(orden);
            servD.save(detalle);
        }
        //Limpiar lista y orden
        orden=new Orden();
        detalles.clear();
        return "usuario/index";
    }
    
    @PostMapping("/search")
    public String searchProducto(@RequestParam String busqueda, Model model){
        log.info("Nombre del Producto: {}", busqueda);
        List<Producto>productos=servP.findAll().
                stream().filter(p->p.getNombre().
                contains(busqueda)).
                collect(Collectors.toList());
        model.addAttribute("productos",productos);
        return "usuario/home";
    }
}
