/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
/**
 *
 * @author crowl
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, length = 20)
    private String nombre;

}
