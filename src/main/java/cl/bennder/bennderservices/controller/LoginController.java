/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.controller;

import cl.bennder.bennderservices.security.JwtTokenUtil;
import cl.bennder.bennderservices.security.JwtUser;
import cl.bennder.bennderservices.services.UsuarioServices;
import cl.bennder.entitybennderwebrest.model.Validacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cl.bennder.bennderservices.services.EmailServices;
import cl.bennder.entitybennderwebrest.request.LoginRequest;
import cl.bennder.entitybennderwebrest.request.RecuperacionPasswordRequest;
import cl.bennder.entitybennderwebrest.response.LoginResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;

/**
 *
 * @author dyanez
 */
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private EmailServices emailServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //.- login
    /*@RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    LoginResponse login(@RequestBody LoginRequest request) {
        log.info("[login] - inicio ");
        LoginResponse response = usuarioServices.validacionUsuario(request);
        log.info("response ->{}", response.toString());
        log.info("[login] - fin ");
        
        return response;
    }*/

    /**
     * Metodo de login que autentica al usuario a travez de spring security con Jwt
     * @param authenticationRequest
     * @return Json con la validacion de usuario
     * @throws AuthenticationException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws AuthenticationException {
        log.info("[login] - inicio ");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setValidacion(new Validacion("0","1","Usuario no encontrado"));
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUser(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getUser());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        loginResponse.setValidacion(new Validacion("0","0","login exitoso"));
        loginResponse.setToken(token);
        return ResponseEntity.ok(loginResponse);

        //TODO: Danilo ahi despues agregas la logica que tenias en el antiguo metodo de login.
    }
    
     /***
     * Servicio utilizado para enviar contraseña a correo de usuario
     * @param  request Usuario correo destinatario 
     * @return 
     */
    @RequestMapping(value = "mail/recuperacionPassword", method = RequestMethod.POST)
    public ValidacionResponse recuperacionPassword(@RequestBody RecuperacionPasswordRequest request) {
        log.info("[mail/recuperacionPassword] - inicio ");
        ValidacionResponse response = emailServices.recuperacionPassword(request);
        log.info("[mail/recuperacionPassword] - fin ");
        return response;
    }
    
}
