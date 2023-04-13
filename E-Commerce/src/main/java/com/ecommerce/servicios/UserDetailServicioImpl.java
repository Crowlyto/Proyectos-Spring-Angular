/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.servicios;

import com.ecommerce.entidades.Usuario;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class UserDetailServicioImpl implements UserDetailsService{
    @Autowired
    private IUsuarioServicio servU;
    @Autowired
    private BCryptPasswordEncoder bCryp;
    @Autowired
    HttpSession session;
    
    private Logger log=LoggerFactory.getLogger(UserDetailServicioImpl.class);
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este el el username");
     Optional<Usuario> optionalUser=servU.findByEmail(username);
     if(optionalUser.isPresent()){
         log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
         session.setAttribute("idUsuario",optionalUser.get().getId());
         Usuario usuario=optionalUser.get();
         return User.builder().
                 username(usuario.getNombre()).
                 password(usuario.getPassword()).roles(usuario.getRol()).build();
     }else {
         throw new UsernameNotFoundException("Usuario no encontrado");
     }
        
    }
    
}
