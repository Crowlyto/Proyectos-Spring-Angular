/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.models;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Noticias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String cuerpo;
    @NotBlank
    private String titulo;
    private String imagen;
    @Temporal(TemporalType.DATE)
    private Date alta;
    @ManyToOne
    private Usuario usuario;
    
    @PrePersist
    public void comoQuieras(){
        this.alta=new Date();
    }
    
}
