/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Producto;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.IOrdenServicio;
import com.ecommerce.servicios.IProductoServicio;
import com.ecommerce.servicios.IUsuarioServicio;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioServicio servU;
    @Autowired
    private IOrdenServicio servO;
    @Autowired
    private IProductoServicio servP;
    
    BCryptPasswordEncoder passEncode=new BCryptPasswordEncoder();

    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        model.addAttribute("productos", servP.findAll());
        Optional<Usuario> user = servU.findById(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        if (user.isPresent()) {
            session.setAttribute("idUsuario", user.get().getId());
            
            if (user.get().getRol().equals("ADMIN")) {
                return "redirect:/administrador";
            } else{
                return "/usuario/index";
            }
        } else {
            log.info("Usuario no existe");
        }
        return "redirect:/";
    }
    
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        log.info("Usuario registro: {}", usuario);
        usuario.setRol("USER");
        usuario.setPassword(passEncode.encode(usuario.getPassword()));
        servU.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {

        return "usuario/login";
    }

    @GetMapping("/compras")
    public String obtenerCompras(HttpSession session, Model model) {
        model.addAttribute("session", session.getAttribute("idUsuario"));
        Usuario usuario=servU.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        List<Orden> ordenes=servO.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleCompras(@PathVariable Integer id, HttpSession session, Model model){
        log.info("Id orden: {}", id );
        Optional<Orden>orden=servO.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        model.addAttribute("session", session.getAttribute("idUsuario"));
        return "usuario/detallecompra";

    }
    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
   
}
