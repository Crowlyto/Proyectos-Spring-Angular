/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellhouse.noticiero.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 *
 * @author crowl
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
                
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.inMemory().withClient("angularapp")
               .secret(passwordEncoder.encode("12345"))
               .scopes("read", "write")
               .authorizedGrantTypes("password", "refresh_token")
               .accessTokenValiditySeconds(3600)
               .refreshTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");  
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        
        JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
        jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
        return jwtAccessTokenConverter;
        
    }
    
    
}
