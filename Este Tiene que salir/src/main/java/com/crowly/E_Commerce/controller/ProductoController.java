/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crowly.E_Commerce.controller;

import com.crowly.E_Commerce.model.Producto;
import com.crowly.E_Commerce.service.IProductoService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author crowl
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoController {
    
    @Autowired
    private IProductoService productoServ;
   
    
    private ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores= new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(),"El campo "+e.getField()+ " "+ e.getDefaultMessage());

        });
        return ResponseEntity.badRequest().body(errores);

    }
    
    @GetMapping
    public ResponseEntity<?> listar(){
      return  ResponseEntity.ok().body(productoServ.findAll());
    }
    
    @GetMapping("/page/{page}")
    public Page<Producto> listar(@PathVariable Integer page){
      return  productoServ.findAll(PageRequest.of(page, 4));
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable Integer id){
        Optional<Producto>produc=productoServ.findById(id);
        if(produc.isPresent()){
            return ResponseEntity.ok().body(produc.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> guardar( @Valid @RequestBody Producto producto,BindingResult result) throws IOException{
        if(result.hasErrors()){
            return this.validar(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServ.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Integer id,@Valid @RequestBody Producto producto, BindingResult result){
        
        if(result.hasErrors()){
            return this.validar(result);
        }
        
        Optional<Producto> produc=productoServ.findById(id);
        if(produc.isPresent()){
            Producto produBBDD=produc.get();
            produBBDD.setNombre(producto.getNombre());
            produBBDD.setDescripcion(producto.getDescripcion());
            productoServ.save(produBBDD);
            return ResponseEntity.status(HttpStatus.CREATED).body(produBBDD);
        }
        return ResponseEntity.notFound().build();
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        Optional<Producto> produ=productoServ.findById(id);
        Producto producto=produ.get();
        if(!produ.isEmpty()){
            String nombreAnterior=producto.getImagen();
            
            if(nombreAnterior!=null && nombreAnterior.length()>0){
                Path imgAnterior=Path.of("img").resolve(nombreAnterior).toAbsolutePath();
                File archivoAnterior= imgAnterior.toFile();
                if(archivoAnterior.exists()&& archivoAnterior.canRead()){
                    archivoAnterior.delete();
                }
            }
            productoServ.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("img") MultipartFile img, @RequestParam ("id") Integer id){
        Producto producto= productoServ.findById(id).get();
        if(!img.isEmpty()){
            String nombreImg= UUID.randomUUID().toString() +"_" + img.getOriginalFilename();
            Path rutaImg= Path.of("img").resolve(nombreImg).toAbsolutePath();
            try {
                Files.copy(img.getInputStream(),rutaImg);
            } catch (IOException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String nombreAnterior=producto.getImagen();
            
            if(nombreAnterior!=null && nombreAnterior.length()>0){
                Path imgAnterior=Path.of("img").resolve(nombreAnterior).toAbsolutePath();
                File archivoAnterior= imgAnterior.toFile();
                if(archivoAnterior.exists()&& archivoAnterior.canRead()){
                    archivoAnterior.delete();
                }
            }
            producto.setImagen(nombreImg);
            productoServ.save(producto);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServ.save(producto));
    }
    
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Path rutaArchivo=Path.of("img").resolve(nombreFoto).toAbsolutePath();
        Resource recurso=null;
        try {
            recurso=new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!recurso.exists()&&!recurso.isReadable()){
            throw new RuntimeException("Error no se puede cargar la imagen");
        }
        
        HttpHeaders cabecera=new  HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
        
    }
    
    
}
