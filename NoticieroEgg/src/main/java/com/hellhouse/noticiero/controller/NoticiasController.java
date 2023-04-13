/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.controller;

import com.hellhouse.noticiero.models.Noticias;
import com.hellhouse.noticiero.service.INoticiaService;
import com.hellhouse.noticiero.service.IUploadFileService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author crowl
 */
@CrossOrigin(origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api")
public class NoticiasController {
    
    @Autowired
    private INoticiaService servN;
    @Autowired
    private IUploadFileService servUp;
    
    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
            
        });
        return ResponseEntity.badRequest().body(errores);
        
    }
    
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(servN.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listaId(@PathVariable Integer id) {
        
        return ResponseEntity.ok().body(servN.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Noticias noticia, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(servN.save(noticia));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> modificar(@PathVariable Integer id, @Valid @RequestBody Noticias noticia, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Optional<Noticias> noti = servN.findById(id);
        if (noti.isPresent()) {
            Noticias notiBD = noti.get();
            notiBD.setTitulo(noticia.getTitulo());
            notiBD.setCuerpo(noticia.getCuerpo());
            servN.save(notiBD);
            return ResponseEntity.status(HttpStatus.CREATED).body(notiBD);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        
        Optional<Noticias> noticia = servN.findById(id);
        if (noticia.isPresent()) {
            Noticias noti=noticia.get();
            String fotoAnterior=noti.getImagen();
            servUp.eliminar(fotoAnterior);
            servN.deleteById(id);
            
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> subirFoto(@RequestParam("archivo") MultipartFile archivo, @RequestParam ("id") Integer id){
        Noticias noticia=servN.findById(id).get();
        if(!archivo.isEmpty()){
            String nombreArchivo=null;
            try {
                nombreArchivo=servUp.copiar(archivo);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
            String fotoAnterior=noticia.getImagen();
            //servUp.eliminar(nombreArchivo);
            noticia.setImagen(nombreArchivo);
            servN.save(noticia);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);
    }
    
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource>verFoto(@PathVariable String nombreFoto){
        Resource recurso=null;
        try {
            recurso= servUp.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders cabecera=new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
    
}
