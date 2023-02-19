/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Orden;
import com.ecommerce.repositorio.IOrdenRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class OrdenServicioImpl implements IOrdenServicio {
    @Autowired
    private IOrdenRepositorio repoO;
    @Override
    public Orden save(Orden orden) {
        return repoO.save(orden);
    }

    @Override
    public List<Orden> findAll() {
      return repoO.findAll();
    }
    
    public String generarNumeroOrden(){
        int numero=0;
        String numeroConcatenado="";
        List<Orden> ordenes= findAll();
        List<Integer> numeros=new ArrayList();
        
        ordenes.stream().forEach(o->numeros.add(Integer.parseInt(o.getNumero())));
        if(ordenes.isEmpty()){
            numero=1;
        }else{
            numero=numeros.stream().max(Integer::compare).get();
            numero++;
        }
        
        if(numero<10){
            numeroConcatenado="000000000"+String.valueOf(numero);
        }else if(numero<100){
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if(numero<1000){
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if(numero<10000){
            numeroConcatenado="000000"+String.valueOf(numero);
        }
        return numeroConcatenado;
    }

}
