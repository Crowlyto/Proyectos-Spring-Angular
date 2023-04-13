/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hellhouse.noticiero.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author crowl
 */
public interface IUploadFileService {
    
    public Resource cargar(String nombreFoto) throws MalformedURLException;
    public String copiar(MultipartFile archivos) throws IOException;
    public boolean eliminar(String nombreFoto);
    public Path getPath(String nombreFoto);
    
}
