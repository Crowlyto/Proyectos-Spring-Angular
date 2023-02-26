/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Orden;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.IOrdenServicio;
import com.ecommerce.servicios.IUsuarioServicio;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.bytebuddy.dynamic.DynamicType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        log.info("Usuario registro: {}", usuario);

        usuario.setRol("USER");
        servU.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {

        return "usuario/login";
    }

    @PostMapping("/acces")
    public String acces(Usuario usuario, HttpSession session) {
        log.info("Acceso : {}", usuario);
        Optional<Usuario> user = servU.findByEmail(usuario.getEmail());

        if (user.isPresent()) {
            session.setAttribute("idUsuario", user.get().getId());
            System.out.println(user);
            if (user.get().getRol().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }

        } else {
            log.info("Usuario no existe");
            return "redirect:/";

        }
        
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
    public String detalleOrden(@PathVariable Integer id, HttpSession session, Model model){
        log.info("Id de la Orden: {}", id);
        
        Optional<Orden> orden= servO.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        
        //session
        model.addAttribute("session", session.getAttribute("idUsuario"));
        return "usuario/detallecompra";
        
    }
}
