/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.controladores;

import com.ecommerce.entidades.Producto;
import com.ecommerce.entidades.Usuario;
import com.ecommerce.servicios.UploadFileServicio;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.servicios.IProductoServicio;
import com.ecommerce.servicios.IUsuarioServicio;
import com.ecommerce.servicios.UsuarioServicioImpl;
import javax.servlet.http.HttpSession;

/**
 *
 * @author crowl
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private IProductoServicio servP;

    @Autowired
    private UploadFileServicio upload;
    @Autowired
    private IUsuarioServicio servU;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", servP.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {

        Usuario user = servU.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        producto.setUsuario(user);
        //imagen
        if (producto.getId() == null) {//cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }
        servP.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Producto producto = new Producto();
        Optional<Producto> respuesta = servP.get(id);
        producto = respuesta.get();
        System.out.println(producto.getNombre());
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    //hay que revisar el metodo de modificar o cargar imagen porque borra la defaul.jpg cuando np deberia y me borra el usuario why? no se
    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p = servP.get(producto.getId()).get();
        if (file.isEmpty()) {//cuando se edita el producto pero no cambiamos la imagen
            producto.setImagen(p.getImagen());
        } else {//Cuando se edita tambien la imagen
            //Eliminar cuando no sea la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(producto.getUsuario());
        servP.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Producto p = new Producto();
        p = servP.get(id).get();
        //Eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }
        servP.delete(id);
        return "redirect:/productos";
    }

}
